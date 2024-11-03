package vertinmod.ui;

import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import basemod.abstracts.CustomSavableRaw;
import basemod.interfaces.ISubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import vertinmod.characters.Vertin;
import java.util.ArrayList;

public class SkinSelectScreen implements ISubscriber, CustomSavable<Integer> {
    private static String[] TEXT;

    private static String[] Special;

    private static final ArrayList<Skin> SKINS = new ArrayList<>();

    public static SkinSelectScreen Inst;

    public Hitbox leftHb;

    public Hitbox rightHb;

    public TextureAtlas atlas;

    public Skeleton skeleton;

    public AnimationStateData stateData;

    public AnimationState state;

    public String curName = "";

    public String nextName = "";

    public String SpecialName = "";

    public int index;

    public Texture usedImg;

    public static Skin getSkin() {
        return SKINS.get(Inst.index);
    }

    public SkinSelectScreen() {
        refresh();
        this.leftHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);
        this.rightHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);
        BaseMod.subscribe(this);
        BaseMod.addSaveField("Vertin:skin", (CustomSavableRaw) this);
    }

    public void loadAnimation(String atlasUrl, String skeletonUrl, float scale) {
    }

    public void refresh() {
        Skin skin = SKINS.get(this.index);
        this.usedImg = ImageMaster.loadImage(skin.charPath);
        Vertin.SELES_STAND = skin.charPath;
        this.curName = skin.name;
        this.SpecialName = skin.special;
        this.nextName = ((Skin) SKINS.get(nextIndex())).name;
        if (AbstractDungeon.player instanceof Vertin) {
            Vertin k = (Vertin) AbstractDungeon.player;
            k.refreshSkin();
        }
    }

    public int prevIndex() {
        return (this.index - 1 < 0) ? (SKINS.size() - 1) : (this.index - 1);
    }

    public int nextIndex() {
        return (this.index + 1 > SKINS.size() - 1) ? 0 : (this.index + 1);
    }

    public void update() {
        float centerX = Settings.WIDTH * 0.8F;
        float centerY = Settings.HEIGHT * 0.5F;
        this.leftHb.move(centerX - 200.0F * Settings.scale, centerY);
        this.rightHb.move(centerX + 200.0F * Settings.scale, centerY);
        updateInput();
    }

    private void updateInput() {
        if (CardCrawlGame.chosenCharacter == Vertin.Enums.VERTIN) {
            this.leftHb.update();
            this.rightHb.update();
            if (this.leftHb.clicked) {
                this.leftHb.clicked = false;
                CardCrawlGame.sound.play("UI_CLICK_1");
                this.index = prevIndex();
                refresh();
            }
            if (this.rightHb.clicked) {
                this.rightHb.clicked = false;
                CardCrawlGame.sound.play("UI_CLICK_1");
                this.index = nextIndex();
                refresh();
            }
            if (InputHelper.justClickedLeft) {
                if (this.leftHb.hovered)
                    this.leftHb.clickStarted = true;
                if (this.rightHb.hovered)
                    this.rightHb.clickStarted = true;
            }
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        Color RC = new Color(0.0F, 204.0F, 255.0F, 1.0F);
        float centerX = Settings.WIDTH * 0.8F;
        float centerY = Settings.HEIGHT * 0.5F;
        float usedx = this.usedImg.getWidth() / 2.0F;
        float usedy = this.usedImg.getWidth() / 2.0F;
        renderSkin(sb, centerX - usedx, centerY - usedy);
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[0], centerX, centerY + 250.0F * Settings.scale, Color.WHITE, 1.25F);
        Color color = Settings.GOLD_COLOR.cpy();
        color.a /= 2.0F;
        float dist = 100.0F * Settings.scale;
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, this.curName, centerX, centerY - 200.0F, RC);
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, this.SpecialName, centerX, centerY - 250.0F, RC);
        if (this.leftHb.hovered) {
            sb.setColor(Color.LIGHT_GRAY);
        } else {
            sb.setColor(Color.WHITE);
        }
        sb.draw(ImageMaster.CF_LEFT_ARROW, this.leftHb.cX - 24.0F, this.leftHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
        if (this.rightHb.hovered) {
            sb.setColor(Color.LIGHT_GRAY);
        } else {
            sb.setColor(Color.WHITE);
        }
        sb.draw(ImageMaster.CF_RIGHT_ARROW, this.rightHb.cX - 24.0F, this.rightHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
        this.rightHb.render(sb);
        this.leftHb.render(sb);
    }

    public void renderSkin(SpriteBatch sb, float x, float y) {
        if (this.usedImg != null)
            sb.draw(this.usedImg, x, y);
    }

    public void onLoad(Integer arg0) {
        this.index = arg0.intValue();
        refresh();
    }

    public Integer onSave() {
        return Integer.valueOf(this.index);
    }

    static {
        String[] special1;
        String[] text1;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            text1 = new String[]{"皮肤列表", "司辰", "第三扇门", "衣帽架"};
            special1 = new String[]{"", "", "", ""};
        } else {
            text1 = new String[]{"Skin List", "Time Keeper", "Third Door", "Clothes Tree"};
            special1 = new String[]{"", "", "", ""};
        }
        Special = special1;
        TEXT = text1;
        SKINS.add(new Skin(0, "TimeKeeper"));
        SKINS.add(new Skin(1, "ThirdDoor"));
        SKINS.add(new Skin(2, "ClothesTree"));
        Inst = new SkinSelectScreen();
    }

    public static class Skin {
        public String charPath;

        public String shoulder;

        public String name;

        public String special;

        public Skin(int index, String charPath) {
            if (charPath.equals("TimeKeeper"))
                this.charPath = "ModVertinResources/img/char/character1.png";
            if (charPath.equals("ThirdDoor"))
                this.charPath = "ModVertinResources/img/char/character.png";
            if (charPath.equals("ClothesTree"))
                this.charPath = "ModVertinResources/img/char/clothes_tree.png";
            this.name = SkinSelectScreen.TEXT[index + 1];
            this.special = SkinSelectScreen.Special[index + 1];
        }
    }

}
