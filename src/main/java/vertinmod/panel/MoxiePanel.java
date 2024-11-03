package vertinmod.panel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;

import static vertinmod.relics.The_Spinning_Wheel.Moxie;
import static vertinmod.relics.The_Spinning_Wheel.Moxie_Max;

public class MoxiePanel extends AbstractPanel {
    private final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getRelicStrings("VertinMod:The_Moxie_Panel").DESCRIPTIONS;

    private int amount;

    private static final Color FONT_COLOR = new Color(1.0F, 1.0F, 0.86F, 1.0F);

    private static BitmapFont expPanelFont = FontHelper.prepFont(34.0F, true);

    public MoxiePanel(float show_x, float show_y, float hide_x, float hide_y, Texture img, boolean startHidden) {
        super(show_x, show_y, hide_x, hide_y, img, startHidden);
        this.amount = 0;
    }

    public void update() {
        if (this.target_x != this.current_x)
            this.current_x = this.target_x;
        if (this.target_y != this.current_y)
            this.current_y = this.target_y;
    }

    public void render(SpriteBatch sb) {
        this.amount = 0;
        FontHelper.renderFontCentered(sb, expPanelFont, DESCRIPTIONS[0], this.current_x / 2 + 20.0F * Settings.scale, 2 * this.current_y - 34.0F * this.amount * Settings.scale, FONT_COLOR);
        for(int i = 0; i < Moxie.size(); i++) {
            if(Moxie.get(i) != 0) {
                this.amount++;
                FontHelper.renderFontCentered(sb, expPanelFont, DESCRIPTIONS[i + 1] + (Moxie.get(i) - 1) + "/" + (Moxie_Max - 1), this.current_x / 2 + 20.0F * Settings.scale, 2 * this.current_y - 34.0F * this.amount * Settings.scale, FONT_COLOR);
            }
        }
    }
}
