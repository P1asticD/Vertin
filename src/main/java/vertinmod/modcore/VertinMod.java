package vertinmod.modcore;

import basemod.BaseMod;
import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.localization.*;
import vertinmod.cards.Arcanists.*;
import vertinmod.cards.Incantations.*;
import vertinmod.cards.VertinCards.*;
import vertinmod.cards.others.*;
import vertinmod.characters.Vertin;
import vertinmod.events.ExpensiveDinner;
import vertinmod.events.MushroomsReplacement;
import vertinmod.events.PaperMemory;
import vertinmod.potions.SPoisonPotion;
import vertinmod.potions.SRegenPotion;
import vertinmod.potions.TransformationPotion;
import vertinmod.relics.*;

import java.nio.charset.StandardCharsets;

import static com.megacrit.cardcrawl.core.Settings.language;
import static vertinmod.characters.Vertin.Enums.VERTIN;
import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;

@SpireInitializer
public class VertinMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber, PostInitializeSubscriber {
    @SpireEnum
    public static AbstractCard.CardTags Fool;
    @SpireEnum
    public static AbstractCard.CardTags JohnTitor;
    @SpireEnum
    public static AbstractCard.CardTags Eagle;
    @SpireEnum
    public static AbstractCard.CardTags Pavia;
    @SpireEnum
    public static AbstractCard.CardTags Mondlicht;
    @SpireEnum
    public static AbstractCard.CardTags Poltergeist;
    @SpireEnum
    public static AbstractCard.CardTags BabyBlue;
    @SpireEnum
    public static AbstractCard.CardTags Dikke;
    @SpireEnum
    public static AbstractCard.CardTags Sonetto;
    @SpireEnum
    public static AbstractCard.CardTags Balloon;
    @SpireEnum
    public static AbstractCard.CardTags Necrologist;
    @SpireEnum
    public static AbstractCard.CardTags Tennant;
    @SpireEnum
    public static AbstractCard.CardTags Diggers;
    @SpireEnum
    public static AbstractCard.CardTags Ulu;
    @SpireEnum
    public static AbstractCard.CardTags Lilya;
    @SpireEnum
    public static AbstractCard.CardTags Knight;
    @SpireEnum
    public static AbstractCard.CardTags Sotheby;
    @SpireEnum
    public static AbstractCard.CardTags Regulus;
    @SpireEnum
    public static AbstractCard.CardTags Centurion;
    @SpireEnum
    public static AbstractCard.CardTags Voyager;
    @SpireEnum
    public static AbstractCard.CardTags NewBabel;
    @SpireEnum
    public static AbstractCard.CardTags BlackDwarf;
    @SpireEnum
    public static AbstractCard.CardTags Ezra;
    @SpireEnum
    public static AbstractCard.CardTags DruvisIII;
    @SpireEnum
    public static AbstractCard.CardTags Ultimate;
    @SpireEnum
    public static AbstractCard.CardTags Arcanist;
    @SpireEnum
    public static AbstractCard.CardTags Vertin;
    @SpireEnum
    public static AbstractCard.CardTags Jessica;

    public VertinMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(VERTIN_CARD, VERTIN_COLOR, VERTIN_COLOR, VERTIN_COLOR, VERTIN_COLOR, VERTIN_COLOR, VERTIN_COLOR, VERTIN_COLOR,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENERGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }
    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "ModVertinResources/img/char/Character_Button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "ModVertinResources/img/char/Character_Portrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "ModVertinResources/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "ModVertinResources/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "ModVertinResources/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "ModVertinResources/img/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "ModVertinResources/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "ModVertinResources/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "ModVertinResources/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "ModVertinResources/img/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENERGY_ORB = "ModVertinResources/img/char/cost_orb.png";
    public static final Color VERTIN_COLOR = new Color(192.0F / 255.0F, 192.0F / 255.0F, 192.0F / 255.0F, 1.0F);

    public static void initialize() {
        new VertinMod();
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new The_Fool());
        BaseMod.addCard(new JohnTitor());
        BaseMod.addCard(new Eagle());
//        BaseMod.addCard(new Pavia());
//        BaseMod.addCard(new Mondlicht());
        BaseMod.addCard(new Poltergeist());
        BaseMod.addCard(new BabyBlue());
        BaseMod.addCard(new Dikke());
        BaseMod.addCard(new Sonetto());
        BaseMod.addCard(new Balloon());
        BaseMod.addCard(new Necrologist());
        BaseMod.addCard(new Tennant());
        BaseMod.addCard(new Diggers());
        BaseMod.addCard(new Lilya());
        BaseMod.addCard(new Knight());
        BaseMod.addCard(new Sotheby());
        BaseMod.addCard(new Regulus());
        BaseMod.addCard(new Centurion());
        BaseMod.addCard(new Voyager());
        BaseMod.addCard(new NewBabel());
        BaseMod.addCard(new BlackDwarf());
        BaseMod.addCard(new Ezra());
        BaseMod.addCard(new DruvisIII());
        BaseMod.addCard(new Jessica());

        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new In_Suitcase());
        BaseMod.addCard(new Alignment());
        BaseMod.addCard(new Incantations_Strike());
        BaseMod.addCard(new Past_Future());
        BaseMod.addCard(new Grand_Orchestra());
        BaseMod.addCard(new Pigeon());
        BaseMod.addCard(new Dissonance());

        BaseMod.addCard(new Sage());
        BaseMod.addCard(new Fool());
        BaseMod.addCard(new Improvised_Show());
        BaseMod.addCard(new Not_Decoration());
        BaseMod.addCard(new True_Genius());
        BaseMod.addCard(new Bytes_65536());
        BaseMod.addCard(new Old_PocketWatch());
        BaseMod.addCard(new Finger_Lens());
        BaseMod.addCard(new Superficiality_And_Reality());
//        BaseMod.addCard(new Dirty_Thing());
//        BaseMod.addCard(new Poor_Sod());
//        BaseMod.addCard(new Noisy_Wolves());
//        BaseMod.addCard(new A_Silver_Bullet());
//        BaseMod.addCard(new An_Axe_Dance());
//        BaseMod.addCard(new Hunting_Wolves());
        BaseMod.addCard(new Prank());
        BaseMod.addCard(new Whisper());
        BaseMod.addCard(new Not_Gentle_Sun());
        BaseMod.addCard(new Play_House());
        BaseMod.addCard(new Cough_Syrup());
        BaseMod.addCard(new Tea_Party());
        BaseMod.addCard(new Power_Power());
        BaseMod.addCard(new Justice());
        BaseMod.addCard(new Maverick_Judge());
        BaseMod.addCard(new Commandment_V());
        BaseMod.addCard(new Exhortations_IX());
        BaseMod.addCard(new Unrestricted_Chant());
        BaseMod.addCard(new Pinata());
        BaseMod.addCard(new Coughing_Weirdo());
        BaseMod.addCard(new Balloon_Party());
//        BaseMod.addCard(new Timely_Farewell());
        BaseMod.addCard(new Timely_Farewell_1());
//        BaseMod.addCard(new By_the_Coffin());
        BaseMod.addCard(new By_the_Coffin_1());
//        BaseMod.addCard(new Whispers_of_Deceased());
        BaseMod.addCard(new Whispers_of_Deceased_1());
        BaseMod.addCard(new Shiny_Diamond());
        BaseMod.addCard(new Galaxy_Bouquet());
        BaseMod.addCard(new Beautiful_Lie());
        BaseMod.addCard(new LongLive_Peace());
        BaseMod.addCard(new Bubblism());
        BaseMod.addCard(new Sweet_Dreams());
        BaseMod.addCard(new Crosswind_Takeoff());
        BaseMod.addCard(new Aerial_Maneuvers());
        BaseMod.addCard(new Canned_Liquor());
        BaseMod.addCard(new Key_of_Su01Be());
        BaseMod.addCard(new Soaring_Witch());
        BaseMod.addCard(new K_Justice());
        BaseMod.addCard(new Glory());
        BaseMod.addCard(new After_AD_778());
//        BaseMod.addCard(new Triple_Dose());
        BaseMod.addCard(new Triple_Dose_1());
//        BaseMod.addCard(new Concentrated_Essence());
        BaseMod.addCard(new Concentrated_Essence_1());
        BaseMod.addCard(new Mix_All());
        BaseMod.addCard(new Treat_Ears());
        BaseMod.addCard(new Challenge_Eyes());
        BaseMod.addCard(new Sleepless_Rave());
        BaseMod.addCard(new Victorious_General());
        BaseMod.addCard(new Outdoor_Superstar());
        BaseMod.addCard(new RealityShow_Premiere());
        BaseMod.addCard(new Refrain());
        BaseMod.addCard(new Starlight_Sonata());
        BaseMod.addCard(new Strings_Galaxy());
        BaseMod.addCard(new Old_Idea());
        BaseMod.addCard(new New_Wave());
        BaseMod.addCard(new Future_Is_Near());
        BaseMod.addCard(new Saturn_Divination());
        BaseMod.addCard(new Mars_Divination());
        BaseMod.addCard(new Lunar_Divination());
        BaseMod.addCard(new Body_Protection());
        BaseMod.addCard(new Spirit_Encourage());
        BaseMod.addCard(new Comprehensive_Care());
        BaseMod.addCard(new Wind_into_Woods());
        BaseMod.addCard(new Early_Dawn());
        BaseMod.addCard(new Silent_Woods());
        BaseMod.addCard(new White_Blankie());
        BaseMod.addCard(new Good_Friends());
        BaseMod.addCard(new Gaze_From_the_Forest());

        BaseMod.addCard(new Paper_Slip());
        BaseMod.addCard(new Adapted_Song());
        BaseMod.addCard(new Jail_Break());
        BaseMod.addCard(new New_World());
        BaseMod.addCard(new Storm());
    }
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ENG";
        if (language == GameLanguage.ZHS) {
            lang = "ZHS";
        }
        String json = Gdx.files.internal("ModVertinResources/localization/" + lang + "/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[])gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords)
                BaseMod.addKeyword("vertinmod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
        }
    }

    public void receiveEditStrings() {
        String lang;
        if (language == GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "ModVertinResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "ModVertinResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "ModVertinResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "ModVertinResources/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "ModVertinResources/localization/" + lang + "/potions.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "ModVertinResources/localization/" + lang + "/events.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "ModVertinResources/localization/" + lang +"/ui.json");
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Vertin(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, VERTIN);
    }

    @Override
    public void receiveEditRelics(){
        BaseMod.addRelicToCustomPool(new First_Melody(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new The_Suitcase(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new The_Spinning_Wheel(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Carrot(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Apple(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Goggles(), VERTIN_CARD);
//        BaseMod.addRelicToCustomPool(new Sparrow(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Sputnik(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new A_Pair_of_Bowknots(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Critters(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Phantom_Honey_Fungus(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Ring(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Fractal_Geometry(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new SPDM_Rules(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Scripture(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Pot(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new S1929W(), VERTIN_CARD);
        BaseMod.addRelicToCustomPool(new Golden_Wine(), VERTIN_CARD);
    }


    public void receivePostInitialize(){
        BaseMod.addPotion(TransformationPotion.class, null, null, null, "VertinMod:TransformationPotion", AbstractPlayer.PlayerClass.DEFECT);
        BaseMod.addPotion(SRegenPotion.class, null, null, null, "VertinMod:SRegenPotion", AbstractPlayer.PlayerClass.DEFECT);
        BaseMod.addPotion(SPoisonPotion.class, null, null, null, "VertinMod:SPoisonPotion", AbstractPlayer.PlayerClass.DEFECT);

        BaseMod.addEvent((new AddEventParams.Builder("VertinMod:MushroomsReplacement", MushroomsReplacement.class))
                .overrideEvent("Mushrooms")
                .eventType(EventUtils.EventType.FULL_REPLACE)
                .create());
        BaseMod.addEvent("VertinMod:PaperMemory", PaperMemory.class, "TheCity");
        BaseMod.addEvent("VertinMod:ExpensiveDinner", ExpensiveDinner.class, "TheCity");
    }
}
