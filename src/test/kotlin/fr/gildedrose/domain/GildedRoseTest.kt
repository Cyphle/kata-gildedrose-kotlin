package fr.gildedrose.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GildedRoseTest {
  private val items = arrayOf(Item("+5 Dexterity Vest", 10, 20), //
          Item("Aged Brie", 2, 0), //
          Item("Elixir of the Mongoose", 5, 7), //
          Item("Sulfuras, Hand of Ragnaros", 0, 80), //
          Item("Sulfuras, Hand of Ragnaros", -1, 80),
          Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
          Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
          Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
          // this conjured item does not work properly yet
          Item("Conjured Mana Cake", 3, 6))
  private val legacyItems = arrayOf(Item("+5 Dexterity Vest", 10, 20), //
          Item("Aged Brie", 2, 0), //
          Item("Elixir of the Mongoose", 5, 7), //
          Item("Sulfuras, Hand of Ragnaros", 0, 80), //
          Item("Sulfuras, Hand of Ragnaros", -1, 80),
          Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
          Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
          Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
          // this conjured item does not work properly yet
          Item("Conjured Mana Cake", 3, 6))

  @Test
  fun `should update quality once`() {
    val app = GildedRose(items)
    assertThat(app.items).isEqualTo(arrayOf(
            Item("+5 Dexterity Vest", 10, 20),
            Item("Aged Brie", 2, 0),
            Item("Elixir of the Mongoose", 5, 7),
            Item("Sulfuras, Hand of Ragnaros", 0, 80),
            Item("Sulfuras, Hand of Ragnaros", -1, 80),
            Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            Item("Conjured Mana Cake", 3, 6)
    ))
  }

  @Test
  fun `should update quality a number of time`() {
    val app = GildedRose(items)
    val legacyApp = GildedRoseLegacy(legacyItems)
    var dayNumber = 1
    while (dayNumber > 0) {
      app.updateQuality()
      legacyApp.updateQuality()
      assertThat(app.items.map { it.name }).isEqualTo(legacyApp.items.map { it.name })
      assertThat(app.items.map { it.quality }).isEqualTo(legacyApp.items.map { it.quality })
      assertThat(app.items.map { it.sellIn }).isEqualTo(legacyApp.items.map { it.sellIn })
      --dayNumber
    }
  }
}