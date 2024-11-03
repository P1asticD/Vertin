package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.actions.MorningStarAction;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Morning_Star_for_the_Night extends CustomCard {
    public static final String ID = ModHelper.makePath(Morning_Star_for_the_Night.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Morning_Star_for_the_Night.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Morning_Star_for_the_Night(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 15;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new MorningStarAction(m, new DamageInfo(p, this.damage)));
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Morning_Star_for_the_Night();
    }
}
