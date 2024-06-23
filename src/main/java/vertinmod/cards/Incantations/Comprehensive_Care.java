package vertinmod.cards.Incantations;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;
import static vertinmod.relics.The_Spinning_Wheel.Moxie_Max;

public class Comprehensive_Care extends CustomCard{
    public static final String ID = ModHelper.makePath(Comprehensive_Care.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Comprehensive_Care.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Comprehensive_Care(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        for (int i = 0; i < Moxie.size(); i++){
            if (i==22)
                continue;
            if (Moxie.get(i) >= 1)
                Moxie.set(i, Math.min(Moxie_Max, Moxie.get(i) + 1));
        }
        addToBot(new ExpertiseAction(p, 10));
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
        }
    }

    public AbstractCard makeCopy(){
        return new Comprehensive_Care();
    }
}
