package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.RegenPotion;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;
import vertinmod.potions.SRegenPotion;
import vertinmod.potions.TransformationPotion;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Sotheby;

public class Concentrated_Essence_1 extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Concentrated_Essence_1.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Concentrated_Essence.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Concentrated_Essence_1(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.cardsToPreview = new Mix_All();
        this.tags.add(Arcanist);
        this.tags.add(Sotheby);
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        /*int chance = 10;
        if (!upgraded)
            addToBot(new ObtainPotionAction(new RegenPotion()));
        else {
            if (AbstractDungeon.cardRandomRng.random(99) <= chance) {
                addToBot(new ObtainPotionAction(new RegenPotion()));
                addToBot(new ObtainPotionAction(new TransformationPotion()));
            }
            else
                addToBot(new ObtainPotionAction(new SRegenPotion()));
        }*/
        if (!upgraded)
            addToBot(new ObtainPotionAction(new RegenPotion()));
        else
            addToBot(new ObtainPotionAction(new SRegenPotion()));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy(){
        return new Concentrated_Essence_1();
    }
}
