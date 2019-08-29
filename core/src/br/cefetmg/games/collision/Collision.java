package br.cefetmg.games.collision;

import com.badlogic.gdx.math.Circle;
import static com.badlogic.gdx.math.MathUtils.clamp;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Utilitário para verificação de colisão.
 *
 * @author fegemo <coutinho@decom.cefetmg.br>
 */
public class Collision {

    /**
     * Verifica se dois círculos em 2D estão colidindo.
     * @param c1 círculo 1
     * @param c2 círculo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean circlesOverlap(Circle c1, Circle c2) {        
        Float dst = Vector2.dst(c1.x, c1.y, c2.x, c2.y);
        Float radiuSum = c1.radius + c2.radius;
        return dst < radiuSum;
    }

    /**
     * Verifica se dois retângulos em 2D estão colidindo.
     * Esta função pode verificar se o eixo X dos dois objetos está colidindo
     * e então se o mesmo ocorre com o eixo Y.
     * @param r1 retângulo 1
     * @param r2 retângulo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean rectsOverlap(Rectangle r1, Rectangle r2) {
        Float r1xMax = r1.x + r1.width;
        Float r2xMax = r2.x + r2.width;
        Float r1yMax = r1.y + r1.height;
        Float r2yMax = r2.y + r2.height;
        boolean isCollidingX = overlap1D(new Vector2(r1.x, r1xMax), new Vector2(r2.x, r2xMax));
        boolean isCollidingY = overlap1D(new Vector2(r1.y, r1yMax), new Vector2(r2.y, r2yMax));
        return isCollidingX && isCollidingY;
    }
    
    public static final boolean overlap1D(Vector2 v1, Vector2 v2){
        return v1.x <= v2.y && v1.y >= v2.x;
    }
    
    public static final boolean circlesRectsOverlap(Circle c, Rectangle r) {
        Vector2 rCenter = new Vector2(r.x + (r.width/2), r.y + (r.height/2));
        Vector2 cCenter = new Vector2(c.x, c.y);
        Vector2 d = cCenter.sub(rCenter);
        
        Float px = clamp(d.x, r.x, r.x+r.width);
        Float py = clamp(d.y, r.y, r.y+r.height);
        Float dst = Vector2.dst(px, py, c.x, c.y);
        
        
        return dst <= c.radius;
    }
    
}
