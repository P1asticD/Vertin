package vertinmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import vertinmod.helpers.ModHelper;

import static java.lang.Integer.MAX_VALUE;
import static vertinmod.relics.The_Spinning_Wheel.Moxie;
import static vertinmod.relics.The_Spinning_Wheel.Moxie_Max;

public class Carrot extends CustomRelic {
    public static final String ID = ModHelper.makePath("Carrot");
    private static final String IMG_PATH = "ModVertinResources/img/relics/Carrot.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Carrot() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(7, true);
        int now_moxie = MAX_VALUE;
        int now_char = -1;
        for (int i = 0; i < Moxie.size(); i++){
            if (Moxie.get(i) > 0){
                if (Moxie.get(i) < now_moxie){
                    now_moxie = Moxie.get(i);
                    now_char = i;
                }
            }
        }
        if(now_char != -1){
            Moxie.set(now_char, Math.min(Moxie.get(now_char) + 3, Moxie_Max));
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Carrot();
    }
}
