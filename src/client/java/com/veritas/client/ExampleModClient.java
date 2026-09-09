package com.veritas.client;

import com.veritas.client.modules.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {
	private static final KeyMapping.Category CATEGORY =
			KeyMapping.Category.register(Identifier.fromNamespaceAndPath("veritas", "veritasclient"));

	private static KeyMapping openScreenKey;
	//private static KeyMapping openTestMenu;

	public static ModuleManager moduleManager;

	public static final Logger LOGGER = LogManager.getLogger("Veritas Client");

	@Override
	public void onInitializeClient() {
		moduleManager = new ModuleManager();

		// Run the Keystrokes renderer before the chat loads.
		HudElementRegistry.attachElementBefore(
				VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath("veritas", "keystrokes_hud"),
				moduleManager.keystrokes::render
		);
		// Run the Coords renderer before the chat loads.
		HudElementRegistry.attachElementBefore(
				VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath("veritas", "coords_hud"),
				moduleManager.coords::render
		);
		// Run the armourDurability renderer before the chat loads.
		HudElementRegistry.attachElementBefore(
				VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath("veritas", "armour_durability"),
				moduleManager.armourDurability::render
		);
		HudElementRegistry.attachElementBefore(
				VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath("veritas", "fps_display"),
				moduleManager.fpsDisplay::render
		);

		// Key to open the mod menu.
		openScreenKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
				"key.veritas.modmenu",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				CATEGORY));

		//openTestMenu = KeyMappingHelper.registerKeyMapping(new KeyMapping(
		//		"key.veritas.testscreen",
		//		InputConstants.Type.KEYSYM,
		//		GLFW.GLFW_KEY_R,
		//		CATEGORY));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openScreenKey.consumeClick()) {
				client.gui.setScreen(new ModMenu(Component.literal("Veritas Client ")));
			}
			//while (openTestMenu.consumeClick()) {
			//	client.gui.setScreen(new TestScreen((Component.literal("Test Screen"))));
			//}
		});
		ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
			LOGGER.info("Client stopping!");
			LOGGER.info("Saving config.");
			ConfigManager.saveConfig();
		});
	}
}