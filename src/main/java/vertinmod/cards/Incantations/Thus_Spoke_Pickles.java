package vertinmod.cards.Incantations;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import vertinmod.helpers.ModHelper;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Ultimate;

public class Thus_Spoke_Pickles extends CustomCard {
    public static final String ID = ModHelper.makePath(Thus_Spoke_Pickles.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Thus_Spoke_Pickles.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Thus_Spoke_Pickles() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.purgeOnUse = true;
        this.tags.add(Ultimate);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean Wtmp = false;
        if(p.hasPower("Weakened")) {
            int tmp = p.getPower("Weakened").amount;
            for(AbstractMonster mo: AbstractDungeon.getMonsters().monsters){
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)mo, tmp, false), tmp, true, AbstractGameAction.AttackEffect.NONE));
            }
        }

        int AddDmg = 0;
        for(AbstractMonster mo: AbstractDungeon.getMonsters().monsters){
            if (mo.currentBlock > 0) {
                AddDmg += mo.currentBlock;
                addToBot(new RemoveAllBlockAction(mo, p));
            }
        }
        if(AddDmg > 0) {
            addToBot(new ApplyPowerAction(p, p, new VigorPower(p, AddDmg), AddDmg));
            if(Wtmp)
                addToBot(new ApplyPowerAction(p, p, new VigorPower(p, AddDmg), AddDmg));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    public AbstractCard makeCopy() {
        return new Thus_Spoke_Pickles();
    }
}