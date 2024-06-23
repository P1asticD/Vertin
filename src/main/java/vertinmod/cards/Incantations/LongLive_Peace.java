package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Diggers;

public class LongLive_Peace extends Ver_CustomCard{
    public static final String ID = ModHelper.makePath(LongLive_Peace.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/LongLive_Peace.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public LongLive_Peace(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 3;
        this.damage = this.baseDamage;
        this.exhaust = true;
        this.cardsToPreview = new Sweet_Dreams();
        this.tags.add(Arcanist);
        this.tags.add(Diggers);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        int m_block = m.currentBlock;
        DamageInfo info = new DamageInfo(p, damage, DamageType.NORMAL);
        addToTop(new DamageAction(m, info));
        if (info.output - m_block > 0) {
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, m_block - info.output), m_block - info.output, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }

    public AbstractCard makeCopy(){
        return new LongLive_Peace();
    }
}
