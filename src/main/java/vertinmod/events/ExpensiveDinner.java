package vertinmod.events;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import vertinmod.relics.Golden_Wine;

public class ExpensiveDinner extends AbstractImageEvent {
    public static final String ID = "VertinMod:ExpensiveDinner";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String DIALOG_1 = DESCRIPTIONS[0];
    private static final String REMIND = DESCRIPTIONS[1];
    private static final String ACCEPT = DESCRIPTIONS[2];
    private static final String REJECT = DESCRIPTIONS[3];
    private CurScreen screen = CurScreen.MAIN;

    private static int goldReward;
    private static int damage;

    private enum CurScreen {
        MAIN, RESULT;
    }

    public ExpensiveDinner(){
        super(NAME, DIALOG_1, "ModVertinResources/img/events/dinner.png");
        if (AbstractDungeon.ascensionLevel >= 15) {
            goldReward = 50;
        } else {
            goldReward = 75;
        }
        damage = AbstractDungeon.player.maxHealth / 10;
        if (damage == 0)
            damage = 1;
        this.imageEventText.setDialogOption(OPTIONS[0] + damage + OPTIONS[1] + goldReward + OPTIONS[2]);
        this.imageEventText.setDialogOption(OPTIONS[3], new Golden_Wine());
        this.imageEventText.setDialogOption(OPTIONS[4]);
    }

    protected void buttonEffect(int buttonPressed){
        switch (this.screen){
            case MAIN:
                switch (buttonPressed){
                    case 0:
                        this.imageEventText.updateBodyText(REMIND);
                        AbstractDungeon.effectList.add(new RainingGoldEffect(goldReward));
                        AbstractDungeon.player.gainGold(goldReward);
                        AbstractDungeon.player.damage(new DamageInfo(null, damage));
                        CardCrawlGame.sound.play("ATTACK_POISON");
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(ACCEPT);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), new Golden_Wine());
                        break;
                    case 2:
                        this.imageEventText.updateBodyText(REJECT);
                        break;
                }
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[5]);
                this.screen = CurScreen.RESULT;
                return;
        }
        openMap();
    }
}
