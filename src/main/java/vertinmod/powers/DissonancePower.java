package vertinmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import vertinmod.actions.DissonanceAction;
import vertinmod.helpers.ModHelper;

import static vertinmod.modcore.VertinMod.Vertin;

public class DissonancePower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makePath("DissonancePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DissonancePower(AbstractCreature owner){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        String path128 = "ModVertinResources/img/powers/Dissonance84.png";
        String path48 = "ModVertinResources/img/powers/Dissonance32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        addToBot((AbstractGameAction)new DissonanceAction());
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        addToBot((AbstractGameAction)new DissonanceAction());
        if (card.hasTag(Vertin)) {
            flash();
            action.exhaustCard = true;
        }
    }
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        addToBot((AbstractGameAction)new DissonanceAction());
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        addToBot((AbstractGameAction)new DissonanceAction());
    }

    public void onCardDraw(AbstractCard card) {
        if (card.hasTag(Vertin))
            card.setCostForTurn(-9);
    }

}
