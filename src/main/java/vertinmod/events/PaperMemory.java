package vertinmod.events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import vertinmod.cards.others.Paper_Slip;

public class PaperMemory extends AbstractImageEvent {
    public static final String ID = "VertinMod:PaperMemory";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String DIALOG_1 = DESCRIPTIONS[0];
    private static final String GET_PAPER = DESCRIPTIONS[1];
    private static final String REFUSE_PAPER = DESCRIPTIONS[2];
    private static final String GET_PAPER_2 = DESCRIPTIONS[3];
    private static final String REFUSE_PAPER_2 = DESCRIPTIONS[4];
    private int screenNum = 0;

    public PaperMemory(){
        super(NAME, DIALOG_1, "ModVertinResources/img/events/classroom.png");
        this.imageEventText.setDialogOption(OPTIONS[0], new Paper_Slip());
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    protected void buttonEffect(int buttonPressed){
        switch (this.screenNum){
            case 0:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(GET_PAPER);
                        getPaper();
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                    case 1:
                        this.imageEventText.updateBodyText(REFUSE_PAPER);
                        this.screenNum = 2;
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                }
            case 1:
                switch (buttonPressed){
                    case 0:
                        this.screenNum = 3;
                        this.imageEventText.updateBodyText(GET_PAPER_2);
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                        return;
                }
            case 2:
                switch (buttonPressed){
                    case 0:
                        this.screenNum = 3;
                        this.imageEventText.updateBodyText(REFUSE_PAPER_2);
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                        return;
                }
        }
        openMap();
    }

    private void getPaper(){
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Paper_Slip(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
    }
}
