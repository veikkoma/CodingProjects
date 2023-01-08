package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	@Test
	public void testMaxQuality() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 0));
		for (int i = 0; i < 51; i++) {
			inn.oneDay();
		}
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by on
		assertEquals("Max quality for Aged Brie", 50, quality);
	}
	
	@Test
	public void testSulfuras() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		for (int i = 0; i < 21; i++) {
			inn.oneDay();
		}
		//access a list of items, get the quality of the one set
		//List<Item> items = inn.getItems();
		//items.get(0).getQuality();
		
		//assert quality has decreased by on
	}
	
	@Test
	public void testBackstage() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		for (int i = 0; i < 51; i++) {
			inn.oneDay();
		}
		//access a list of items, get the quality of the one set
		//List<Item> items = inn.getItems();
		//int quality = items.get(0).getQuality();
		
		//assert quality has decreased by on
	}
	
	@Test 
	public void testMinQuality() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 3, 6));
		for (int i = 6; i > 0; i--) {
			inn.oneDay();
		}
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
	}
}
