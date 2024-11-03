package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Song_of_Wandering extends CustomCard {
    public static final String ID = ModHelper.makePath(Song_of_Wandering.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Song_of_Wandering.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Song_of_Wandering(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.purgeOnUse = true;
        this.magicNumber = this.baseMagicNumber = 3;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.discardPile.size() > 0) {
            addToBot((AbstractGameAction)new EmptyDeckShuffleAction());
            addToBot((AbstractGameAction)new ShuffleAction(AbstractDungeon.player.drawPile, false));
        }
        addToBot((AbstractGameAction)new ScryAction(AbstractDungeon.player.masterDeck.size()));
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
    }

    public void upgrade(){
        if (!this.upgraded) {
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Song_of_Wandering();
    }
}
