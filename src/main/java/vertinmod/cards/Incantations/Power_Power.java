package vertinmod.cards.Incantations;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import vertinmod.cards.Ver_CustomCard;
import vertinmod.helpers.ModHelper;

import java.util.function.Consumer;

import static vertinmod.characters.Vertin.Enums.VERTIN_CARD;
import static vertinmod.modcore.VertinMod.Arcanist;
import static vertinmod.modcore.VertinMod.Dikke;

public class Power_Power extends Ver_CustomCard {
    public static final String ID = ModHelper.makePath(Power_Power.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModVertinResources/img/cards/Power_Power.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = VERTIN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Power_Power(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 6;
        this.damage = this.baseDamage;
        this.exhaust = true;
        this.cardsToPreview = new Maverick_Judge();
        this.tags.add(Arcanist);
        this.tags.add(Dikke);
    }
    public static Consumer<Integer> getDamageCallback(AbstractMonster monster) {
        return amount -> {
            if (amount.intValue() > 0 && !monster.isDeadOrEscaped() && !monster.halfDead && monster.hasPower("Minion"))
                AbstractDungeon.actionManager.addToTop((AbstractGameAction)new InstantKillAction((AbstractCreature)monster));
        };
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(null);
        for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
            if (!monster.isDeadOrEscaped() && !monster.halfDead) {
                int dmg = this.multiDamage[(AbstractDungeon.getMonsters()).monsters.indexOf(monster)];
                addToBot((AbstractGameAction)new DamageCallbackAction((AbstractCreature)monster, new DamageInfo((AbstractCreature)p, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE, getDamageCallback(monster)));
            }
        }
    }

    public void upgrade(){
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }

    public AbstractCard makeCopy(){
        return new Power_Power();
    }
}
