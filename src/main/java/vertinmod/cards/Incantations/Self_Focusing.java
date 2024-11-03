package vertinmod.cards.Incantations;

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
import static vertinmod.modcore.VertinMod.Mercuria;

public class Self_Focusing extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Self_Focusing.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Self_Focusing.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;

    public Self_Focusing(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardsToPreview = new Morning_Star_for_the_Night();
        this.selfRetain = true;
        this.tags.add(Arcanist);
        this.tags.add(Mercuria);
    }

    public void use(AbstractPlayer p, AbstractMonster m){

    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return this.upgraded;
    }

    public void upgrade(){
        if (!upgraded){
            upgradeBaseCost(0);
            this.exhaust = true;
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        if (AbstractDungeon.player.hand.contains((AbstractCard)this)) {
            int index = AbstractDungeon.player.hand.group.indexOf(this);
            int startIndex = Math.max(0, index - this.magicNumber);
            int endIndex = Math.min(AbstractDungeon.player.hand.size() - 1, index + this.magicNumber);
            if(!this.upgraded) {
                ((AbstractCard) AbstractDungeon.player.hand.group.get(startIndex)).retain = true;
                ((AbstractCard) AbstractDungeon.player.hand.group.get(startIndex)).upgrade();
            }
            else {
                for (int i = startIndex; i <= endIndex; i++) {
                    ((AbstractCard) AbstractDungeon.player.hand.group.get(i)).retain = true;
                    ((AbstractCard) AbstractDungeon.player.hand.group.get(i)).upgrade();
                }
            }
        }
    }

    public AbstractCard makeCopy(){
        return new Self_Focusing();
    }
}
