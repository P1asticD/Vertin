package vertinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vertinmod.modcore.VertinMod;

public class MorphingAction extends AbstractGameAction {
    private AbstractPlayer p;

    public static final Logger logger = LogManager.getLogger(VertinMod.class);

    public MorphingAction() {
        this.p = AbstractDungeon.player;
    }

    public void update() {
        this.p.img = ImageMaster.loadImage("ModVertinResources/img/char/clothes_tree.png");
        this.isDone = true;
    }
}
