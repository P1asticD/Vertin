package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Jessica;

public class White_Blankie extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(White_Blankie.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/White_Blankie.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public White_Blankie(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Gaze_From_the_Forest();
        this.tags.add(Arcanist);
        this.tags.add(Jessica);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        if (!m.hasPower("Poison"))
            addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        else
            addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber - 5), this.magicNumber - 5, AbstractGameAction.AttackEffect.POISON));
    }

    public void upgrade(){
        if (!this.upgraded){
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
            upgradeMagicNumber(2);
        }
    }

    public AbstractCard makeCopy(){
        return new White_Blankie();
    }

}
