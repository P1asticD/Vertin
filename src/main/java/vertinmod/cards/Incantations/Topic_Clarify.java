package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.*;

public class Topic_Clarify extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Topic_Clarify.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Topic_Clarify.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Topic_Clarify(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = 8;
        this.magicNumber = this.baseMagicNumber = 2;
        this.cardsToPreview = new Thus_Spoke_Pickles();
        this.tags.add(Arcanist);
        this.tags.add(Pickles);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new WeakPower((AbstractCreature)abstractPlayer, this.magicNumber, false)));
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (!m.isDeadOrEscaped())
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)abstractPlayer, (AbstractPower)new WeakPower((AbstractCreature)m, this.magicNumber, false)));
        }
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, this.block));
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            upgradeBlock(4);
        }
    }

    public AbstractCard makeCopy(){
        return new Topic_Clarify();
    }
}
