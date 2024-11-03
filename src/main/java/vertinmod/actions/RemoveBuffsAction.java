package vertinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RemoveBuffsAction extends AbstractGameAction {
    private AbstractCreature c;

    public RemoveBuffsAction(AbstractCreature c){
        this.c = c;
        this.duration = 0.5F;
    }

    @Override
    public void update(){
        for (AbstractPower p: this.c.powers){
            if (p.ID == "Artifact" || p.ID == "Barricade" || p.ID == "Intangible" || p.ID == "Metallicize" || p.ID == "Plated Armor" || p.ID == "Ritual" || p.ID == "Thorns")
                addToTop(new RemoveSpecificPowerAction(this.c, this.c, p.ID));
            if (p.ID == "Strength")
                if (p.amount > 0)
                    addToTop(new RemoveSpecificPowerAction(this.c, this.c, p.ID));
        }
        this.isDone = true;

    }
}
