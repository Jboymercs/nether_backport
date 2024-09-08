# Event Analysis

This file contains an analysis on the various forge event busses. This first
table lists events that have listeners, in order of most listeners. Further
specifics on the listenrs. This data is anonymous, and is not automatically
submitted to any online service.

| Event Name                                   | Listener Count |
|----------------------------------------------|----------------|
| TickEvent$ClientTickEvent                    | 5              |
| ConfigChangedEvent$OnConfigChangedEvent      | 3              |
| WorldEvent$Load                              | 3              |
| TextureStitchEvent$Pre                       | 2              |
| AnvilUpdateEvent                             | 2              |
| AttachCapabilitiesEvent                      | 2              |
| LivingEvent$LivingUpdateEvent                | 2              |
| NetherAPIRegistryEvent$Nether                | 2              |
| WorldEvent$Unload                            | 2              |
| WorldEvent$Save                              | 2              |
| EntityJoinWorldEvent                         | 2              |
| GuiScreenEvent$InitGuiEvent$Post             | 1              |
| PlayerEvent$PlayerLoggedInEvent              | 1              |
| PlayerInteractEvent                          | 1              |
| PlayerContainerEvent$Open                    | 1              |
| ChunkDataEvent                               | 1              |
| LivingEvent$LivingJumpEvent                  | 1              |
| GuiOpenEvent                                 | 1              |
| PlayerSetSpawnEvent                          | 1              |
| TickEvent$ServerTickEvent                    | 1              |
| ModelBakeEvent                               | 1              |
| FMLNetworkEvent$ClientConnectedToServerEvent | 1              |
| CommandEvent                                 | 1              |
| ColorHandlerEvent$Block                      | 1              |
| NetherAPIRegistryEvent$End                   | 1              |
| PlayerInteractEvent$RightClickBlock          | 1              |
| ConfigChangedEvent                           | 1              |
| ColorHandlerEvent$Item                       | 1              |
| ChunkEvent$Unload                            | 1              |
| PlayerEvent$PlayerRespawnEvent               | 1              |
| InputEvent$KeyInputEvent                     | 1              |
| TickEvent                                    | 1              |
| LivingDeathEvent                             | 1              |
| PlaySoundAtEntityEvent                       | 1              |
| PlayerEvent$Clone                            | 1              |
| PlayerInteractEvent$EntityInteract           | 1              |
| LivingDestroyBlockEvent                      | 1              |
| LivingEquipmentChangeEvent                   | 1              |


## GuiScreenEvent$InitGuiEvent$Post
| Owner            | Method    | Location                   | Priority | Source                                                    | RecieveCanceled |
|------------------|-----------|----------------------------|----------|-----------------------------------------------------------|-----------------|
| Had Enough Items | onGuiInit | mezz.jei.input.MouseHelper | normal   | had-enough-items-557549-4571247_mapped_stable_39-1.12.jar | false           |


## TextureStitchEvent$Pre
| Owner            | Method                  | Location                                             | Priority | Source                                                    | RecieveCanceled |
|------------------|-------------------------|------------------------------------------------------|----------|-----------------------------------------------------------|-----------------|
| Future MC        | onTextureStitchEventPre | thedarkcolour.futuremc.registry.RegistryEventHandler | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar         | false           |
| Had Enough Items | handleTextureRemap      | mezz.jei.startup.ProxyCommonClient                   | normal   | had-enough-items-557549-4571247_mapped_stable_39-1.12.jar | false           |


## PlayerEvent$PlayerLoggedInEvent
| Owner           | Method      | Location | Priority | Source                                                     | RecieveCanceled |
|-----------------|-------------|----------|----------|------------------------------------------------------------|-----------------|
| Minecraft Forge | playerLogin | forge    | normal   | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |


## PlayerInteractEvent
| Owner     | Method           | Location                                 | Priority | Source                                   | RecieveCanceled |
|-----------|------------------|------------------------------------------|----------|------------------------------------------|-----------------|
| WorldEdit | onPlayerInteract | com.sk89q.worldedit.forge.ForgeWorldEdit | normal   | worldedit-forge-mc1.12.2-6.1.10-dist.jar | false           |


## PlayerContainerEvent$Open
| Owner     | Method          | Location                            | Priority | Source                                            | RecieveCanceled |
|-----------|-----------------|-------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | onContainerOpen | thedarkcolour.futuremc.event.Events | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## ChunkDataEvent
| Owner     | Method            | Location                                     | Priority | Source                                            | RecieveCanceled |
|-----------|-------------------|----------------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | onChunkEntityLoad | thedarkcolour.futuremc.world.OldWorldHandler | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## LivingEvent$LivingJumpEvent
| Owner     | Method           | Location                            | Priority | Source                                            | RecieveCanceled |
|-----------|------------------|-------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | preventHoneyJump | thedarkcolour.futuremc.event.Events | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## ConfigChangedEvent$OnConfigChangedEvent
| Owner            | Method          | Location                                                    | Priority | Source                                                     | RecieveCanceled |
|------------------|-----------------|-------------------------------------------------------------|----------|------------------------------------------------------------|-----------------|
| Had Enough Items | onConfigChanged | mezz.jei.startup.ProxyCommonClient                          | normal   | had-enough-items-557549-4571247_mapped_stable_39-1.12.jar  | false           |
| Minecraft Forge  | onConfigChanged | forge                                                       | normal   | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |
| Nether API       | sync            | git.jbredwards.nether_api.mod.common.config.NetherAPIConfig | normal   | nether-API-f2f48d7b8b_mapped_stable_39-1.12.jar            | false           |


## GuiOpenEvent
| Owner     | Method    | Location                                   | Priority | Source                                            | RecieveCanceled |
|-----------|-----------|--------------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | onGuiOpen | thedarkcolour.futuremc.client.ClientEvents | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## AnvilUpdateEvent
| Owner                   | Method                   | Location                                  | Priority | Source                           | RecieveCanceled |
|-------------------------|--------------------------|-------------------------------------------|----------|----------------------------------|-----------------|
| Bookshelf               | onAnvilUpdate            | net.darkhax.bookshelf.Bookshelf           | normal   | Bookshelf-1.12.2-2.3.590 (1).jar | false           |
| Unseens Nether Backport | addNetheriteAnvilRecipes | com.unseen.nb.common.event.AnvilNetherite | normal   | nb-1.12.2-0.0.1.jar              | false           |


## PlayerSetSpawnEvent
| Owner                   | Method              | Location                                      | Priority | Source              | RecieveCanceled |
|-------------------------|---------------------|-----------------------------------------------|----------|---------------------|-----------------|
| Unseens Nether Backport | setSpawnCheckAnchor | com.unseen.nb.common.event.EventRespawnAnchor | normal   | nb-1.12.2-0.0.1.jar | false           |


## AttachCapabilitiesEvent
| Owner                   | Method             | Location                                                | Priority | Source                                            | RecieveCanceled |
|-------------------------|--------------------|---------------------------------------------------------|----------|---------------------------------------------------|-----------------|
| Unseens Nether Backport | attachCapabilities | com.unseen.nb.common.event.EventRespawnAnchor           | normal   | nb-1.12.2-0.0.1.jar                               | false           |
| Future MC               | attachCapability   | thedarkcolour.futuremc.capability.FPlayerData$Companion | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## LivingEvent$LivingUpdateEvent
| Owner                   | Method                         | Location                                    | Priority | Source              | RecieveCanceled |
|-------------------------|--------------------------------|---------------------------------------------|----------|---------------------|-----------------|
| Unseens Nether Backport | addSoulSpeed                   | com.unseen.nb.common.event.EventOnSoulSpeed | normal   | nb-1.12.2-0.0.1.jar | false           |
| Unseens Nether Backport | removeNetheriteHorseArmorBuffs | com.unseen.nb.common.event.EventOnSoulSpeed | normal   | nb-1.12.2-0.0.1.jar | false           |


## TickEvent$ServerTickEvent
| Owner           | Method       | Location                                       | Priority | Source                                                     | RecieveCanceled |
|-----------------|--------------|------------------------------------------------|----------|------------------------------------------------------------|-----------------|
| Minecraft Forge | onServerTick | net.minecraftforge.common.ForgeInternalHandler | normal   | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |


## ModelBakeEvent
| Owner     | Method      | Location                                   | Priority | Source                                            | RecieveCanceled |
|-----------|-------------|--------------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | onModelBake | thedarkcolour.futuremc.client.ClientEvents | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## FMLNetworkEvent$ClientConnectedToServerEvent
| Owner            | Method                    | Location                           | Priority | Source                                                    | RecieveCanceled |
|------------------|---------------------------|------------------------------------|----------|-----------------------------------------------------------|-----------------|
| Had Enough Items | onClientConnectedToServer | mezz.jei.startup.ProxyCommonClient | normal   | had-enough-items-557549-4571247_mapped_stable_39-1.12.jar | false           |


## CommandEvent
| Owner     | Method         | Location                                 | Priority | Source                                   | RecieveCanceled |
|-----------|----------------|------------------------------------------|----------|------------------------------------------|-----------------|
| WorldEdit | onCommandEvent | com.sk89q.worldedit.forge.ForgeWorldEdit | normal   | worldedit-forge-mc1.12.2-6.1.10-dist.jar | false           |


## ColorHandlerEvent$Block
| Owner      | Method          | Location                                                | Priority | Source                                          | RecieveCanceled |
|------------|-----------------|---------------------------------------------------------|----------|-------------------------------------------------|-----------------|
| Nether API | applyLavaColors | git.jbredwards.nether_api.mod.client.ClientEventHandler | lowest   | nether-API-f2f48d7b8b_mapped_stable_39-1.12.jar | false           |


## NetherAPIRegistryEvent$End
| Owner      | Method               | Location                                          | Priority | Source                                          | RecieveCanceled |
|------------|----------------------|---------------------------------------------------|----------|-------------------------------------------------|-----------------|
| Nether API | registerHardcodedEnd | git.jbredwards.nether_api.mod.common.EventHandler | highest  | nether-API-f2f48d7b8b_mapped_stable_39-1.12.jar | false           |


## TickEvent$ClientTickEvent
| Owner           | Method                | Location                                                        | Priority | Source                                                     | RecieveCanceled |
|-----------------|-----------------------|-----------------------------------------------------------------|----------|------------------------------------------------------------|-----------------|
| Nether API      | resetCurrentMusicType | git.jbredwards.nether_api.mod.client.audio.NetherMusicHandler   | normal   | nether-API-f2f48d7b8b_mapped_stable_39-1.12.jar            | false           |
| Minecraft Forge | checkSettings         | net.minecraftforge.common.ForgeInternalHandler                  | normal   | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |
| Nether API      | resetCurrentMusicType | git.jbredwards.nether_api.mod.client.audio.TheEndMusicHandler   | normal   | nether-API-f2f48d7b8b_mapped_stable_39-1.12.jar            | false           |
| Nether API      | onPlayerTick          | git.jbredwards.nether_api.mod.client.audio.BiomeAmbienceHandler | normal   | nether-API-f2f48d7b8b_mapped_stable_39-1.12.jar            | false           |
| Bookshelf       | onClientTick          | net.darkhax.bookshelf.Bookshelf                                 | normal   | Bookshelf-1.12.2-2.3.590 (1).jar                           | false           |


## NetherAPIRegistryEvent$Nether
| Owner                   | Method                  | Location                                          | Priority | Source                                          | RecieveCanceled |
|-------------------------|-------------------------|---------------------------------------------------|----------|-------------------------------------------------|-----------------|
| Nether API              | registerHardcodedNether | git.jbredwards.nether_api.mod.common.EventHandler | highest  | nether-API-f2f48d7b8b_mapped_stable_39-1.12.jar | false           |
| Unseens Nether Backport | onNetherAPIRegistry     | com.unseen.nb.handler.ApiEventHandler             | highest  | nb-1.12.2-0.0.1.jar                             | false           |


## PlayerInteractEvent$RightClickBlock
| Owner     | Method        | Location                            | Priority | Source                                            | RecieveCanceled |
|-----------|---------------|-------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | onLogStripped | thedarkcolour.futuremc.event.Events | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## ConfigChangedEvent
| Owner     | Method          | Location                            | Priority | Source                                            | RecieveCanceled |
|-----------|-----------------|-------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | onConfigChanged | thedarkcolour.futuremc.event.Events | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## ColorHandlerEvent$Item
| Owner           | Method               | Location                                     | Priority | Source                                                     | RecieveCanceled |
|-----------------|----------------------|----------------------------------------------|----------|------------------------------------------------------------|-----------------|
| Minecraft Forge | registerItemHandlers | net.minecraftforge.client.ForgeClientHandler | normal   | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |


## ChunkEvent$Unload
| Owner           | Method        | Location                                       | Priority | Source                                                     | RecieveCanceled |
|-----------------|---------------|------------------------------------------------|----------|------------------------------------------------------------|-----------------|
| Minecraft Forge | onChunkUnload | net.minecraftforge.common.ForgeInternalHandler | normal   | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |


## WorldEvent$Load
| Owner           | Method          | Location                                       | Priority | Source                                                     | RecieveCanceled |
|-----------------|-----------------|------------------------------------------------|----------|------------------------------------------------------------|-----------------|
| Bookshelf       | onWorldLoaded   | net.darkhax.bookshelf.Bookshelf                | normal   | Bookshelf-1.12.2-2.3.590 (1).jar                           | false           |
| Future MC       | onWorldLoad     | thedarkcolour.futuremc.event.Events            | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar          | false           |
| Minecraft Forge | onDimensionLoad | net.minecraftforge.common.ForgeInternalHandler | highest  | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |


## WorldEvent$Unload
| Owner           | Method            | Location                                       | Priority | Source                                                     | RecieveCanceled |
|-----------------|-------------------|------------------------------------------------|----------|------------------------------------------------------------|-----------------|
| Minecraft Forge | onDimensionUnload | net.minecraftforge.common.ForgeInternalHandler | highest  | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |
| Future MC       | onWorldUnload     | thedarkcolour.futuremc.event.Events            | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar          | false           |


## PlayerEvent$PlayerRespawnEvent
| Owner                   | Method                            | Location                                      | Priority | Source              | RecieveCanceled |
|-------------------------|-----------------------------------|-----------------------------------------------|----------|---------------------|-----------------|
| Unseens Nether Backport | onRespawnPreformAnchorTeleporting | com.unseen.nb.common.event.EventRespawnAnchor | normal   | nb-1.12.2-0.0.1.jar | false           |


## InputEvent$KeyInputEvent
| Owner     | Method | Location                             | Priority | Source                                   | RecieveCanceled |
|-----------|--------|--------------------------------------|----------|------------------------------------------|-----------------|
| WorldEdit | onKey  | com.sk89q.worldedit.forge.KeyHandler | normal   | worldedit-forge-mc1.12.2-6.1.10-dist.jar | false           |


## TickEvent
| Owner     | Method    | Location                                  | Priority | Source                                   | RecieveCanceled |
|-----------|-----------|-------------------------------------------|----------|------------------------------------------|-----------------|
| WorldEdit | tickStart | com.sk89q.worldedit.forge.ThreadSafeCache | normal   | worldedit-forge-mc1.12.2-6.1.10-dist.jar | false           |


## LivingDeathEvent
| Owner     | Method                       | Location                            | Priority | Source                                            | RecieveCanceled |
|-----------|------------------------------|-------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | spawnWitherRoseOrDropTrident | thedarkcolour.futuremc.event.Events | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## PlaySoundAtEntityEvent
| Owner     | Method                | Location                            | Priority | Source                                            | RecieveCanceled |
|-----------|-----------------------|-------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | onHoneyBottleConsumed | thedarkcolour.futuremc.event.Events | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## WorldEvent$Save
| Owner            | Method          | Location                                       | Priority | Source                                                     | RecieveCanceled |
|------------------|-----------------|------------------------------------------------|----------|------------------------------------------------------------|-----------------|
| Had Enough Items | onWorldSave     | mezz.jei.startup.ProxyCommonClient             | normal   | had-enough-items-557549-4571247_mapped_stable_39-1.12.jar  | false           |
| Minecraft Forge  | onDimensionSave | net.minecraftforge.common.ForgeInternalHandler | highest  | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |


## EntityJoinWorldEvent
| Owner                   | Method             | Location                                       | Priority | Source                                                     | RecieveCanceled |
|-------------------------|--------------------|------------------------------------------------|----------|------------------------------------------------------------|-----------------|
| Minecraft Forge         | onEntityJoinWorld  | net.minecraftforge.common.ForgeInternalHandler | highest  | forge-1.12.2-14.23.5.2860_mapped_stable_39-1.12-recomp.jar | false           |
| Unseens Nether Backport | turnItemsFireproof | com.unseen.nb.common.EntityEvents              | normal   | nb-1.12.2-0.0.1.jar                                        | false           |


## PlayerEvent$Clone
| Owner                   | Method  | Location                                      | Priority | Source              | RecieveCanceled |
|-------------------------|---------|-----------------------------------------------|----------|---------------------|-----------------|
| Unseens Nether Backport | onClone | com.unseen.nb.common.event.EventRespawnAnchor | normal   | nb-1.12.2-0.0.1.jar | false           |


## PlayerInteractEvent$EntityInteract
| Owner     | Method        | Location                            | Priority | Source                                            | RecieveCanceled |
|-----------|---------------|-------------------------------------|----------|---------------------------------------------------|-----------------|
| Future MC | healIronGolem | thedarkcolour.futuremc.event.Events | normal   | futuremc-310059-5013081_mapped_stable_39-1.12.jar | false           |


## LivingDestroyBlockEvent
| Owner      | Method                      | Location                                          | Priority | Source                                          | RecieveCanceled |
|------------|-----------------------------|---------------------------------------------------|----------|-------------------------------------------------|-----------------|
| Nether API | handleDragonResistantBlocks | git.jbredwards.nether_api.mod.common.EventHandler | highest  | nether-API-f2f48d7b8b_mapped_stable_39-1.12.jar | false           |


## LivingEquipmentChangeEvent
| Owner                   | Method                        | Location                                    | Priority | Source              | RecieveCanceled |
|-------------------------|-------------------------------|---------------------------------------------|----------|---------------------|-----------------|
| Unseens Nether Backport | adjustSoulSpeedUponBootChange | com.unseen.nb.common.event.EventOnSoulSpeed | normal   | nb-1.12.2-0.0.1.jar | false           |
