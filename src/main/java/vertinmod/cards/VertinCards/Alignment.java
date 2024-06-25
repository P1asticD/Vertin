package vertinmod.cards.VertinCards;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import vertinmod.cards.Arcanists.*;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import java.util.ArrayList;
import java.util.Arrays;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Vertin;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;

public class Alignment extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Alignment.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Alignment.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.NONE;

    public Alignment(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.selfRetain = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(Vertin);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        ArrayList<AbstractCard> Arcanists = new ArrayList<>(Arrays.asList(new The_Fool(), new JohnTitor(), new Eagle(), new Pavia(), new Mondlicht(), new Poltergeist(), new BabyBlue(), new Dikke(), new Sonetto(), new Balloon(), new Necrologist(), new Tennant(), new Diggers(), new Ulu(), new Lilya(), new Knight(), new Sotheby(), new Regulus(), new Centurion(), new Voyager(), new NewBabel(), new BlackDwarf(), new Ezra(), new DruvisIII(), new Jessica()));
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        for (int i = 0; i < Moxie.size(); i++){
            if (Moxie.get(i) >= 1){
                stanceChoices.add(Arcanists.get(i));
            }
        }
        if (this.upgraded)
            for (AbstractCard c : stanceChoices)
                c.upgrade();
        if (stanceChoices.size() > 0) {
            addToBot(new ChooseOneAction(stanceChoices));
        }
        else
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CARD_STRINGS.EXTENDED_DESCRIPTION[1], true));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeMagicNumber(1);
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Alignment();
    }
}
