package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;
import vertinmod.patches.ScryActionPatch;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.*;

public class Hey_Punji extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Hey_Punji.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Hey_Punji.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Hey_Punji(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Song_of_Wandering();
        this.tags.add(Arcanist);
        this.tags.add(Kanjira);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ScryActionPatch.num = 0;
        addToBot((AbstractGameAction)new ScryAction(this.magicNumber));
        int sc = this.magicNumber;
        addToBot(new AbstractGameAction() {
            public void update() {
                this.isDone = true;
                int Poison_num = ScryActionPatch.num;
                if(Poison_num < sc)
                    addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new PoisonPower((AbstractCreature)m, (AbstractCreature)p, Poison_num), Poison_num, AbstractGameAction.AttackEffect.POISON));
                else
                    addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new PoisonPower((AbstractCreature)m, (AbstractCreature)p, 2*Poison_num), 2*Poison_num, AbstractGameAction.AttackEffect.POISON));
            }
        });
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(4);
        }
    }

    public AbstractCard makeCopy(){
        return new Hey_Punji();
    }
}
