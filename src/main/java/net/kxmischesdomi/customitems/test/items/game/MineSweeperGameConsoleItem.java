package net.kxmischesdomi.customitems.test.items.game;

import net.kxmischesdomi.customitems.management.menu.ClickableInventory;
import net.kxmischesdomi.customitems.utils.bukkit.event.PlayerInventoryClickEvent;
import net.kxmischesdomi.customitems.utils.bukkit.inventory.InventoryFillUtils;
import net.kxmischesdomi.customitems.utils.bukkit.item.ItemBuilder;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.CustomRecipeChoices.WoolChoice;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfo.ShapedRecipeInfo;
import net.kxmischesdomi.customitems.utils.bukkit.recipe.RecipeInfoContainer;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class MineSweeperGameConsoleItem extends InventoryGameConsoleItem {

	private static JavaPlugin plugin;

	public MineSweeperGameConsoleItem() {
		plugin = getPlugin();
	}

	@Nonnull
	@Override
	protected ItemStack createItemStack() {
		return ItemBuilder.createItem(Material.BONE, "Minesweeper Console");
	}

	@Override
	public int getCustomModelData() {
		return 123;
	}

	@Override
	protected void createRecipes(@Nonnull RecipeInfoContainer container) {
		container.addRecipe(
				new ShapedRecipeInfo().setShape("wtw", "sns", "wrw")
						.setIngredient('w', new WoolChoice())
						.setIngredient('t', Material.REDSTONE_TORCH)
						.setIngredient('s', Material.SHEARS)
						.setIngredient('n', Material.TNT)
						.setIngredient('r', Material.REDSTONE)
		);
	}

	@Nonnull
	@Override
	public String getKey() {
		return "minesweeper";
	}

	@Override
	public int getInventorySize() {
		return 6*9;
	}

	@Override
	public String getInventoryTitle() {
		return "Minesweeper";
	}

	@Override
	public void fillInventory(@Nonnull ClickableInventory clickableInventory) {
		InventoryFillUtils.fillInventory(clickableInventory.getInventory(), Material.GRAY_STAINED_GLASS_PANE);

		MinesweeperClickAction clickAction = new MinesweeperClickAction(clickableInventory);
		clickableInventory.setOtherSlotAction(clickAction);

		final int bombsCount = 8;
		List<Integer> emptySlots = new ArrayList<>();
		for (int i = 0; i < clickableInventory.getInventory().getSize(); i++) {
			emptySlots.add(i);
		}

		for (int i = 0; i < bombsCount; i++) {
			int slot = emptySlots.remove(ThreadLocalRandom.current().nextInt(emptySlots.size()));
			clickableInventory.setAction(slot, clickAction);
//			clickableInventory.getInventory().setItem(slot, ItemBuilder.createItem(Material.GRAY_STAINED_GLASS_PANE, "§cHidden Bomb"));
		}

	}

	public static class MinesweeperClickAction implements Consumer<PlayerInventoryClickEvent> {

		private final ClickableInventory clickableInventory;
		boolean ended = false;

		public MinesweeperClickAction(ClickableInventory clickableInventory) {
			this.clickableInventory = clickableInventory;
		}

		@Override
		public void accept(PlayerInventoryClickEvent event) {
			Player player = event.getPlayer();
			if (ended) {
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.5F, 1F);
				return;
			}

			Inventory inventory = clickableInventory.getInventory();
			int slot = event.getSlot();
			ItemStack item = inventory.getItem(slot);
			if (item == null) return;

			if (event.getClick() == ClickType.RIGHT || event.getClick() == ClickType.SHIFT_RIGHT) {
				if (item.getType() == Material.ORANGE_STAINED_GLASS_PANE) {
					inventory.setItem(slot, ItemBuilder.createItem(Material.GRAY_STAINED_GLASS_PANE, "§0"));
				} else if (item.getType() == Material.GRAY_STAINED_GLASS_PANE) {
					inventory.setItem(slot, ItemBuilder.createItem(Material.ORANGE_STAINED_GLASS_PANE, "§6Marked"));
				}
				return;
			}

			if (item.getType() == Material.ORANGE_STAINED_GLASS_PANE) {
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.5F, 1F);
				return;
			}

			if (clickableInventory.getSlotActions().containsKey(slot)) {
				ended = true;
				explodeBombs(player, slot);

				return;
			}

			boolean oneSlot = !evaluateSlot(event.getSlot());

			if (checkForWin()) {
				ended = true;
				transformBombs(ItemBuilder.createItem(Material.FIREWORK_ROCKET, "§cBomb"), player, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 0.5F, 1, isBomb(slot) ? slot : -1);
				return;
			}

			if (oneSlot) {
				player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 0.5f, 1);
			} else {
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
			}

		}

		/**
		 * @return if more than one slot was evaluated
		 */
		private boolean evaluateSlot(@Nonnegative int slot) {
			Inventory inventory = clickableInventory.getInventory();

			ItemStack item = inventory.getItem(slot);
			if (item == null) return false;
			if (item.getType() != Material.GRAY_STAINED_GLASS_PANE && item.getType() != Material.ORANGE_STAINED_GLASS_PANE) return false;

			List<Integer> neighboursSlots = getNeighboursSlots(slot);

			int bombCount = getBombCount(neighboursSlots);
			if (bombCount != 0) {
				inventory.setItem(slot, getDisplayItemForBombCount(bombCount));
				return false;
			}
			inventory.setItem(slot, ItemBuilder.createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, "§0"));

			for (int neighboursSlot : neighboursSlots) {
				evaluateSlot(neighboursSlot);
			}

			return !neighboursSlots.isEmpty();
		}

		private boolean isBomb(@Nonnull int slot) {
			return clickableInventory.getSlotActions().containsKey(slot);
		}

		private ItemStack getDisplayItemForBombCount(int bombCount) {
			switch (bombCount) {
				case 1:
					return ItemBuilder.createItem(Material.BLUE_STAINED_GLASS_PANE, "§91");
				case 2:
					return ItemBuilder.createItem(Material.LIME_STAINED_GLASS_PANE, "§a2");
				case 3:
					return ItemBuilder.createItem(Material.RED_STAINED_GLASS_PANE, "§c3");
				case 4:
					return ItemBuilder.createItem(Material.BROWN_STAINED_GLASS_PANE, "§84");
				default:
					return ItemBuilder.createItem(Material.BLACK_STAINED_GLASS_PANE, "§0" + bombCount);
			}
		}

		private int getBombCount(@Nonnull List<Integer> slots) {
			int bombCount = 0;
			for (Integer slot : slots) {
				if (clickableInventory.getSlotActions().containsKey(slot)) bombCount++;
			}
			return bombCount;
		}

		private List<Integer> getNeighboursSlots(int slot) {
			List<Integer> list = new LinkedList<>();

			for (int yIndex = -1; yIndex <= 1; yIndex++) {
				int yAddition = yIndex == -1 ? -9 : yIndex == 1 ? 9 : 0;

				int slotWithoutX = slot + yAddition;

				for (int x = -1; x <= 1; x++) {
					int currentSlot = x + slotWithoutX;
					if (slot == 8 && currentSlot == 0) continue; // hardcoded bug fix i couldn't figure out
					if (getRow(x + slotWithoutX) != getRow(slotWithoutX)) continue;
					if (currentSlot < 0 || currentSlot > 53) continue;
					list.add(currentSlot);
				}
			}

			return list;
		}

		private int getRow(int slot) {
			return slot / 9;
		}

		private void explodeBombs(@Nonnull Player player, int startBomb) {
			transformBombs(ItemBuilder.createItem(Material.TNT, "§cBomb"), player, Sound.ENTITY_GENERIC_EXPLODE, 0.3F, 1.2F, startBomb);
		}

		private int currentStartBomb = -1;

		private void transformBombs(@Nonnull ItemStack itemStack, @Nonnull Player player, @Nonnull Sound sound, float volume, float pitch, int startBomb) {
			currentStartBomb = startBomb;
			Bukkit.getScheduler().runTaskTimer(plugin, task -> {
				Integer[] slotsToExplode = (Integer[]) ArrayUtils.removeElement(clickableInventory.getSlotActions().keySet().toArray(new Integer[0]), -1);
				if (slotsToExplode.length <= 0) {
					task.cancel();
					return;
				}
				int bombToExplode = currentStartBomb != -1 ? currentStartBomb : slotsToExplode[(ThreadLocalRandom.current().nextInt(slotsToExplode.length))];
				currentStartBomb = -1;

				clickableInventory.getSlotActions().remove(bombToExplode);
				clickableInventory.getInventory().setItem(bombToExplode, itemStack);
				player.playSound(player.getLocation(), sound, volume, pitch);

			}, 0, 5);
		}

		private boolean checkForWin() {

			Inventory inventory = clickableInventory.getInventory();

			for (int slot = 0; slot < inventory.getSize(); slot++) {

				ItemStack item = inventory.getItem(slot);
				if (item == null) return false;

				boolean isRevealed = item.getType() != Material.GRAY_STAINED_GLASS_PANE;
				boolean isBomb = clickableInventory.getSlotActions().containsKey(slot);

				if (!isBomb) {
					if (!isRevealed) return false;
				}

			}

			return true;
		}

	}

}
    