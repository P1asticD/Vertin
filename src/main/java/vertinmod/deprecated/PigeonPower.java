package vertinmod.deprecated;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import vertinmod.helpers.ModHelper;


public class PigeonPower extends TwoAmountPower {
    public static final String POWER_ID = ModHelper.makePath("PigeonPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static int Count_Moxie_Use;

    public PigeonPower(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.amount2 = 0;
        String path128 = "ModVertinResources/img/powers/Pigeon84.png";
        String path48 = "ModVertinResources/img/powers/Pigeon32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription(){
        this.amount2 = Count_Moxie_Use;
        this.description = DESCRIPTIONS[0] + amount +  DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }

    public static void GetEnergy(){
        if (Count_Moxie_Use >= 3){
            AbstractDungeon.player.gainEnergy(Count_Moxie_Use / 3);
            Count_Moxie_Use %= 3;
        }
    }

    public void onVictory(){
        Count_Moxie_Use = 0;
    }
}
