package vertinmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import vertinmod.helpers.ModHelper;

public class BalloonPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makePath("BalloonPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BalloonPower(AbstractCreature owner){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        String path128 = "ModVertinResources/img/powers/Balloon84.png";
        String path48 = "ModVertinResources/img/powers/Balloon32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractPlayer p = (AbstractPlayer)this.owner;
            if (p.hasPower("Strength")) {
                int tmp = p.getPower("Strength").amount;
                if(tmp > 0){
                    addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -tmp), -tmp));
                    addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, tmp), tmp));
                }
            }
        }
    }

    public void atStartOfTurn() {
        AbstractPlayer p = (AbstractPlayer)this.owner;
        if (p.hasPower("Thorns")) {
            int tmp = p.getPower("Thorns").amount;
            if(tmp > 0){
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, tmp), tmp));
                addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, -tmp), -tmp));
            }
        }
    }
}
