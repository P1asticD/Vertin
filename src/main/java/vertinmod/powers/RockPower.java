package vertinmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import vertinmod.helpers.ModHelper;

public class RockPower extends AbstractPower{
    public static final String POWER_ID = ModHelper.makePath("RockPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RockPower(AbstractCreature owner, int Amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = Amount;
        String path128 = "ModVertinResources/img/powers/Rock84.png";
        String path48 = "ModVertinResources/img/powers/Rock32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (damage > 1.0F)
            damage = 0.0F;
        return damage;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type.equals(AbstractCard.CardType.ATTACK)){
            addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "VertinMod:RockPower"));
        }
    }

    public void atEndOfRound() {
        flash();
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "VertinMod:RockPower"));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, "VertinMod:RockPower", 1));
        }
    }
}
