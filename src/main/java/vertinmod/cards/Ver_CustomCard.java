package vertinmod.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import vertinmod.helpers.ModHelper;
import vertinmod.strings.VertinCardSignStrings;

import static vertinmod.modcore.VertinMod.Vertin;
import static vertinmod.relics.The_Suitcase.MorningStarCount;


public abstract class Ver_CustomCard extends CustomCard {
    private final CardStrings cardStrings;
    public String FLAVOR_TEXT;
    public String LABEL_TEXT;
    protected String cardSign = null;

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

        VertinCardSignStrings sign = VertinCardSignStrings.get(this.cardID);
        if (sign != null)
            this.cardSign = sign.SIGN;
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

    public void renderCardSign(SpriteBatch sb, float xPos, float yPos, float yOffsetBase, float scale) {
        if (!ModHelper.enableSpellCardSignDisplay())
            return;
        if (this.cardSign != null) {
            if (this.isFlipped || this.isLocked || this.transparency <= 0.0F)
                return;
            float offsetY = yOffsetBase * Settings.scale * scale / 2.0F;
            BitmapFont.BitmapFontData fontData = FontHelper.cardTitleFont.getData();
            float originalScale = fontData.scaleX;
            float scaleMultiplier = 0.8F;
            fontData.setScale(scaleMultiplier * scale * 0.85F);
            Color color = Settings.CREAM_COLOR.cpy();
            color.a = this.transparency;
            FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, this.cardSign, xPos, yPos, 0.0F, offsetY, this.angle, true, color);
            fontData.setScale(originalScale);
        }
    }

    public void renderCardSign(SpriteBatch sb) {
        renderCardSign(sb, this.current_x, this.current_y, 400.0F, this.drawScale);
    }

    public void render(SpriteBatch sb) {
        super.render(sb);
        renderCardSign(sb);
    }

    public void renderInLibrary(SpriteBatch sb) {
        super.renderInLibrary(sb);
        if (!SingleCardViewPopup.isViewingUpgrade || !this.isSeen || this.isLocked)
            renderCardSign(sb);
    }

    public boolean canUpgrade(){
        return (!this.upgraded && (this.hasTag(Vertin) || this.type == CardType.POWER)) || (MorningStarCount == 1);
    }
}
