package vertinmod.cards.Incantations;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
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
import static vertinmod.modcore.VertinMod.Voyager;

public class Refrain extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Refrain.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Refrain.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Refrain(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.cardsToPreview = new Strings_Galaxy();
        this.tags.add(Arcanist);
        this.tags.add(Voyager);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        for(AbstractMonster mo:AbstractDungeon.getMonsters().monsters){
            if (mo != null && mo.intent != AbstractMonster.Intent.ATTACK && mo.intent != AbstractMonster.Intent.ATTACK_BUFF && mo.intent != AbstractMonster.Intent.ATTACK_DEBUFF && mo.intent != AbstractMonster.Intent.ATTACK_DEFEND){
                addToTop(new StunMonsterAction(mo, p, 1));
            }
        }
    }

    public void upgrade(){
        if (!upgraded){
            upgradeName();
            upgradeBaseCost(1);
        }
    }

    public AbstractCard makeCopy(){
        return new Refrain();
    }
}
