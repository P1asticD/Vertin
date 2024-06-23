package vertinmod.cards.Incantations;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;
import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Poltergeist;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;
import static vertinmod.relics.The_Spinning_Wheel.Moxie_Max;

public class Whisper extends Ver_CustomCard{
    public static final String ID = ModHelper.makePath(Whisper.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Whisper.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Whisper(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 15;
        this.block = this.baseBlock;
        this.cardsToPreview = new Not_Gentle_Sun();
        this.tags.add(Arcanist);
        this.tags.add(Poltergeist);
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public void triggerOnExhaust() {
        if (Moxie.get(5) >= 1)
            Moxie.set(5, Math.min(Moxie.get(5) + 1, Moxie_Max));
    }

    public void upgrade(){
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(6);
        }
    }

    public AbstractCard makeCopy(){
        return new Whisper();
    }
}
