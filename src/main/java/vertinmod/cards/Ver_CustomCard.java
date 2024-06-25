package vertinmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import static vertinmod.modcore.VertinMod.Vertin;


public abstract class Ver_CustomCard extends CustomCard {
    private final CardStrings cardStrings;
    public String FLAVOR_TEXT;
    public String LABEL_TEXT;

    public Ver_CustomCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        try {
            this.FLAVOR_TEXT = cardStrings.EXTENDED_DESCRIPTION[0];
        } catch (NullPointerException e) {
            this.FLAVOR_TEXT = "";
        }
        try {
            this.LABEL_TEXT = cardStrings.EXTENDED_DESCRIPTION[0];
        } catch (NullPointerException e) {
            this.LABEL_TEXT = "";
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            String UPGRADE_DESCRIPTION = null;
            try {
                UPGRADE_DESCRIPTION = this.cardStrings.UPGRADE_DESCRIPTION;
            } catch (NullPointerException nullPointerException) {}
            if (UPGRADE_DESCRIPTION != null) {
                this.rawDescription = UPGRADE_DESCRIPTION;
                initializeDescription();
            }
            try {
                if (this.cardStrings.EXTENDED_DESCRIPTION.length >= 2)
                    this.FLAVOR_TEXT = this.cardStrings.EXTENDED_DESCRIPTION[1];
            } catch (NullPointerException e) {
                this.FLAVOR_TEXT = "";
            }
        }
    }

    public boolean canUpgrade(){
        return !this.upgraded && (this.hasTag(Vertin) || this.type == CardType.POWER);
    }
}
