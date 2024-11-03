package vertinmod.cards.Arcanists;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.TOKEN_CARD;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;
import static vertinmod.relics.The_Spinning_Wheel.Moxie_Max;

public class Ulu extends CustomCard {
    public static final String ID = ModHelper.makePath(Ulu.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Ulu.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TOKEN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;

    public Ulu(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        onChoseThisOption();
    }

    public void onChoseThisOption(){
        if (Moxie.get(13) >= 1)
            Moxie.set(13, Math.min(Moxie.get(13) + magicNumber, Moxie_Max));
    }

    public void upgrade(){
        if(!upgraded){
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy(){
        return new Ulu();
    }
}
