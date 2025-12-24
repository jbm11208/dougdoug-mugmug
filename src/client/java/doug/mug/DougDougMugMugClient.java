package doug.mug;

import doug.mug.client.model.BrickBrickModel;
import doug.mug.client.model.CowCowModel;
import doug.mug.client.model.MelonMelonModel;
import doug.mug.client.model.MugMugModel;
import doug.mug.client.render.*;
import doug.mug.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class DougDougMugMugClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MUG_MUG, MugMugModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BRICK_BRICK, BrickBrickModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.COW_COW, CowCowModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MELON_MELON, MelonMelonModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.BRICK_BRICK, BrickBrickRenderer::new);
		EntityRendererRegistry.register(ModEntities.MUG_MUG, MugMugRenderer::new);
		EntityRendererRegistry.register(ModEntities.COW_COW, CowCowRenderer::new);
		EntityRendererRegistry.register(ModEntities.MELON_MELON, MelonMelonRenderer::new);
	}
}