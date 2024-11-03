package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;
import vertinmod.powers.NihilismPower;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.*;

public class Nihilism_Abuse extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Nihilism_Abuse.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Nihilism_Abuse.png";
    private static final int COST = 3;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Nihilism_Abuse(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.cardsToPreview = new Thus_Spoke_Pickles();
        this.tags.add(Arcanist);
        this.tags.add(Pickles);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new NihilismPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            upgradeBaseCost(2);
        }
    }

    public AbstractCard makeCopy(){
        return new Nihilism_Abuse();
    }
}
