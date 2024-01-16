package vertinmod.deprecated;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.RegenPotion;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Necrologist;

public class Timely_Farewell extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Timely_Farewell.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Timely_Farewell.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Timely_Farewell(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.tags.add(AbstractCard.CardTags.HEALING);
        this.tags.add(Arcanist);
        this.tags.add(Necrologist);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ObtainPotionAction(new RegenPotion()));
    }

    public void upgrade(){
        if (!this.upgraded){
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    public AbstractCard makeCopy(){
        return new Timely_Farewell();
    }
}
