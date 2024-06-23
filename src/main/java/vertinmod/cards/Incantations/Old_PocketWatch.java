package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTags.HEALING;
import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Eagle;

public class Old_PocketWatch extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Old_PocketWatch.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Old_PocketWatch.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Old_PocketWatch(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 16;
        this.damage = this.baseDamage;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Superficiality_And_Reality();
        this.tags.add(Eagle);
        this.tags.add(Arcanist);
        this.tags.add(HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean inDebuff = false;
        for (AbstractPower pow : m.powers){
            if (pow.type == AbstractPower.PowerType.DEBUFF){
                inDebuff = true;
                break;
            }
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL)));
        if(inDebuff)
            addToBot(new HealAction(p, p, this.magicNumber));
    }

    public void upgrade(){
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
            this.upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new Old_PocketWatch();
    }
}
