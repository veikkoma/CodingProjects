import sys

import pygame
from random import randint


class Keravihu:
    def __init__(self):
        pygame.init()

        # Set the window size
        window_size = (800, 600)

        # Create the window
        self.screen = pygame.display.set_mode(window_size)

        # Set the title
        pygame.display.set_caption('Start Screen')

        # Show the start screen
        self.show_start_screen()

    def show_start_screen(self):
        # Create a start button
        start_button = pygame.Rect(300, 400, 200, 50)

        # Create a close button
        close_button = pygame.Rect(300, 500, 200, 50)

        # Set the font
        font = pygame.font.Font(None, 36)

        # Set the colors
        WHITE = (255, 255, 255)
        BLACK = (0, 0, 0)

        # Set the text for the start button
        start_text = font.render('Start', True, WHITE)
        start_text_rect = start_text.get_rect()
        start_text_rect.center = (400, 425)

        # Set the text for the close button
        close_text = font.render('Close', True, WHITE)
        close_text_rect = close_text.get_rect()
        close_text_rect.center = (400, 525)

        while True:
            # Handle events
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                if event.type == pygame.MOUSEBUTTONDOWN:
                    # Check if the start button was clicked
                    if start_button.collidepoint(event.pos):
                        # Load the images
                        self.lataa_kuvat()
                        # Start a new game
                        self.uusi_peli()
                        # Run the main loop for the game
                        self.silmukka()
                    # Check if the close button was clicked
                    elif close_button.collidepoint(event.pos):
                        pygame.quit()
                        sys.exit()

            # Draw the start screen
            self.screen.fill(WHITE)
            pygame.draw.rect(self.screen, BLACK, start_button)
            pygame.draw.rect(self.screen, BLACK, close_button)
            self.screen.blit(start_text, start_text_rect)
            self.screen.blit(close_text, close_text_rect)
            pygame.display.flip()

    # Download png pictures in memory!
    def lataa_kuvat(self):
        self.hirvio = pygame.image.load("hirvio.png")
        self.robo = pygame.image.load("robo.png")
        self.kolikko = pygame.image.load("kolikko.png")

    # Loop through the game
    def silmukka(self):
        while True:
            self.tutki_tapahtumat()
            self.piirra_naytto()
            self.tutki_osumat()
            if self.hirvio_x + self.hirvio.get_width() > self.robo_x and self.hirvio_x < self.robo_x + self.robo.get_width() and self.hirvio_y + self.hirvio.get_height() > self.robo_y and self.hirvio_y < self.robo_y + self.robo.get_height():
                # Monster and robot are colliding, go back to start screen
                self.show_start_screen()
            self.peli_lapi()

    # Robot moving
    def tutki_tapahtumat(self):
        pygame.key.set_repeat(1, 10)
        for tapahtuma in pygame.event.get():
            if tapahtuma.type == pygame.KEYDOWN:
                if tapahtuma.key == pygame.K_LEFT:
                    self.liiku(-2)
                if tapahtuma.key == pygame.K_RIGHT:
                    self.liiku(2)
                if tapahtuma.key == pygame.K_SPACE:
                    self.uusi_peli()
                if tapahtuma.key == pygame.K_ESCAPE:
                    exit()
            if tapahtuma.type == pygame.QUIT:
                exit()

    def liiku(self, liike_x):
        if self.robo_x + liike_x > 0 and self.robo.get_width() + self.robo_x + liike_x < 640:
            self.robo_x += liike_x

    # Examines the hits
    def tutki_osumat(self):
        for i in range(self.kolikotmaara):
            if self.kolikot[i][1] + self.kolikko.get_height() >= self.robo_y:
                self.robo_keski = self.robo_x + self.kolikko.get_width() / 2
                self.kolikko_keski = self.kolikot[i][0] + self.kolikko.get_width() / 2
                if abs(self.robo_keski - self.kolikko_keski) <= (self.robo.get_width() + self.kolikko.get_width()) / 2:
                    self.kolikot[i][0] = randint(0, 640 - self.kolikko.get_width())
                    self.kolikot[i][1] = -randint(100, 1000)
                    self.pistelaskuri += 1

        for i in range(self.hirviotmaara):
            if self.hirviot[i][1] + self.hirvio.get_height() >= self.robo_y:
                self.robo_keski = self.robo_x + self.hirvio.get_width() / 2
                self.hirvio_keski = self.hirviot[i][0] + self.hirvio.get_width() / 2
                if abs(self.robo_keski - self.hirvio_keski) <= (self.robo.get_width() + self.hirvio.get_width()) / 2:
                    self.hirviot[i][0] = randint(0, 640 - self.hirvio.get_width())
                    self.hirviot[i][1] = -randint(100, 1000)
                    exit()

    # Game passed!
    def peli_lapi(self):
        if self.pistelaskuri > 9:
            return True
        else:
            return False

    def uusi_peli(self):
        WIDTH = 640
        HEIGHT = 480

        self.robo_x = 350
        self.robo_y = 450
        self.hirvio_x = randint(0, self.screen.get_width() - self.hirvio.get_width())
        self.hirvio_y = -50

        self.robo_x = round(WIDTH / 2 - self.robo.get_width() / 2, 0)
        self.robo_y = round(HEIGHT - self.robo.get_height(), 0)

        self.naytto = pygame.display.set_mode((WIDTH, HEIGHT))

        self.fontti = pygame.font.SysFont("Arial", 24)

        self.pistelaskuri = 0
        self.kolikotmaara = 10
        self.kolikot = []
        for i in range(self.kolikotmaara):
            self.kolikot.append([randint(0, WIDTH - self.kolikko.get_width()), -randint(100, 1000)])

        self.hirviotmaara = 10
        self.hirviot = []
        for i in range(self.hirviotmaara):
            self.hirviot.append([randint(0, WIDTH - self.hirvio.get_width()), -randint(100, 1000)])

    # Building a view
    def piirra_naytto(self):
        if self.peli_lapi():
            teksti = self.fontti.render("Onnittelut, läpäisit pelin!", True, (255, 255, 0))
            self.naytto.blit(teksti, (200, 50))

            teksti = self.fontti.render("Space = uusi peli", True, (255, 255, 255))
            self.naytto.blit(teksti, (220, 10))

            teksti = self.fontti.render("Esc = sulje peli", True, (255, 255, 255))
            self.naytto.blit(teksti, (450, 10))
        else:
            self.naytto.fill((255, 0, 255))
            self.naytto.blit(self.robo, (self.robo_x, self.robo_y))
            for i in range(self.kolikotmaara):
                self.naytto.blit(self.kolikko, (self.kolikot[i][0], self.kolikot[i][1]))
                if self.kolikot[i][1] < 480:
                    self.kolikot[i][1] += 0.25
                else:
                    self.kolikot[i][1] = -randint(100, 1000)

            for i in range(self.hirviotmaara):
                self.naytto.blit(self.hirvio, (self.hirviot[i][0], self.hirviot[i][1]))
                if self.hirviot[i][1] < 480:
                    self.hirviot[i][1] += 0.15
                else:
                    self.hirviot[i][1] = -randint(100, 1000)

            teksti = self.fontti.render(f"Pisteet: " + str(self.pistelaskuri), True, (255, 255, 255))
            self.naytto.blit(teksti, (25, 10))
        pygame.display.flip()


Keravihu()
