package tk.t11e.pei;

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.api.event.PlayerAttemptCondenserSetEvent;
import moze_intel.projecte.api.event.PlayerAttemptLearnEvent;
import moze_intel.projecte.api.event.PlayerKnowledgeChangeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import the_fireplace.grandeconomy.api.GrandEconomyApi;
import the_fireplace.grandeconomy.api.event.BalanceChangeEvent;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.UUID;

@Mod("pei")
public class Main {

    private final Class<?> entityClass = Class.forName("net.minecraft.entity.Entity");

    public Main() throws ClassNotFoundException {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onBalanceChange);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onKnowledgeChange);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCondenserSet);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLearn);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInteract);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onJoin);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBalanceChange(BalanceChangeEvent event) {
        UUID uuid = event.getAccountId();
        IKnowledgeProvider knowledgeProvider = ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(uuid);
        knowledgeProvider.setEmc(BigInteger.valueOf(event.getNewBalance()));
    }

    @SubscribeEvent
    public void onKnowledgeChange(PlayerKnowledgeChangeEvent event) {
        UUID uuid = event.getPlayerUUID();
        IKnowledgeProvider knowledgeProvider = ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(uuid);
        GrandEconomyApi.setBalance(uuid, knowledgeProvider.getEmc().longValue(), true);
    }

    @SubscribeEvent
    public void onCondenserSet(PlayerAttemptCondenserSetEvent event) {
        try {
            UUID uuid = (UUID) entityClass.getMethod("func_110124_au").invoke(event.getPlayer());
            IKnowledgeProvider knowledgeProvider = ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(uuid);
            GrandEconomyApi.setBalance(uuid, knowledgeProvider.getEmc().longValue(), true);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onLearn(PlayerAttemptLearnEvent event) {
        try {
            UUID uuid = (UUID) entityClass.getMethod("func_110124_au").invoke(event.getPlayer());
            IKnowledgeProvider knowledgeProvider = ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(uuid);
            GrandEconomyApi.setBalance(uuid, knowledgeProvider.getEmc().longValue(), true);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        try {
            UUID uuid = (UUID) entityClass.getMethod("func_110124_au").invoke(event.getPlayer());
            IKnowledgeProvider knowledgeProvider = ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(uuid);
            GrandEconomyApi.setBalance(uuid, knowledgeProvider.getEmc().longValue(), true);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
        try {
            UUID uuid = (UUID) entityClass.getMethod("func_110124_au").invoke(event.getPlayer());
            IKnowledgeProvider knowledgeProvider = ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(uuid);
            GrandEconomyApi.setBalance(uuid, knowledgeProvider.getEmc().longValue(), true);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }
    }
}
