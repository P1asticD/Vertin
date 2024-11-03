package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Centurion;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;
import static vertinmod.relics.The_Spinning_Wheel.Moxie_Max;

public class Outdoor_Superstar extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Outdoor_Superstar.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Outdoor_Superstar.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Outdoor_Superstar(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.cardsToPreview = new RealityShow_Premiere();
        this.tags.add(Arcanist);
        this.tags.add(Centurion);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        int count = 0;
        int moxie = Moxie.get(18);
        if (moxie != 0) {
            for (AbstractMonster m2 : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (!m2.isDeadOrEscaped()) {
                    count++;
                }
            }
            if (moxie + count + 1 <= Moxie_Max) {
                Moxie.set(18, moxie + count);
            } else {
                Moxie.set(18, Moxie_Max);
                int ex = moxie + count - Moxie_Max;
                p.gainEnergy(ex);
                addToBot(new DrawCardAction(p, ex));
            }
        }
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            this.exhaust = false;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy(){
        return new Outdoor_Superstar();
    }
}
