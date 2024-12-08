package rabbitescape.engine.logic;

import org.junit.Test;

import static rabbitescape.engine.util.WorldAssertions.assertWorldEvolvesLike;

public class TestPausing
{
    @Test
    public void Pauser_stands_still()  // 5초 멈춘 뒤 다시 움직임
    {
        assertWorldEvolvesLike(
            " ru " + "\n" +
                "####",

            "  r " + "\n" +
                "####",

            "  r " + "\n" +
                "####",

            "  r " + "\n" +
                "####",

            "  r " + "\n" +
                "####",

            "  r " + "\n" +
                "####",

            "  r " + "\n" +
                "####",

            "   r" + "\n" +
                "####"
        );
    }

    @Test
    public void Pauser_others_passing()
    {
        assertWorldEvolvesLike(
            "r ru " + "\n" +
                "#####",

            " r r " + "\n" +
                "#####",

            "  rr " + "\n" +
                "#####",

            "   r " + "\n" +
                "#####",

            "   rr" + "\n" + // 통과
                "#####"
        );
    }

    @Test
    public void Pauser_prevents_others_passing_on_slope_up_start()
    {
        assertWorldEvolvesLike(
            "    #" + "\n" +
            "r r*#" + "\n" +
                "#####" + "\n" +
                ":*=u/",

            "    #" + "\n" +
            " r r#" + "\n" +
                "#####",

            "    #" + "\n" +
            "  rr#" + "\n" +
                "#####",

            "    #" + "\n" +
                "   r#" + "\n" +
                "#####",

            "    #" + "\n" +
                "   r#" + "\n" +
                "#####",

            "    #" + "\n" +
                "  jr#" + "\n" +
                "#####",

            "    #" + "\n" +
                " j r#" + "\n" +
                "#####"
        );
    }

    @Test
    public void Pauser_prevents_others_passing_on_slope_up_mid()
    {
        assertWorldEvolvesLike(
            "     /" + "\n" +
                "    *#" + "\n" +
                "r r/##" + "\n" +
                "######" + "\n" +
                ":*=u/",

            "     /" + "\n" +
                "    u#" + "\n" +
                " r r##" + "\n" +
                "######",

            "     /" + "\n" +
                "    r#" + "\n" +
                "  r/##" + "\n" +
                "######",

            "     /" + "\n" +
                "    r#" + "\n" +
                "   r##" + "\n" +
                "######",

            "     /" + "\n" +
                "    r#" + "\n" +
                "   /##" + "\n" +
                "######",

            "     r" + "\n" +
                "    r#" + "\n" +
                "   /##" + "\n" +
                "######"
            );
    }

    @Test
    public void Pauser_prevents_others_passing_on_slope_down_start()
    {
        assertWorldEvolvesLike(
            "r r  " + "\n" +
                "###* " + "\n" +
                "#####" + "\n" +
                ":*=u\\",

            " r   " + "\n" +
                "###r " + "\n" +
                "#####",

            "  r  " + "\n" +
                "###r " + "\n" +
                "#####",

            "     " + "\n" +
                "###r " + "\n" +
                "#####",

            "     " + "\n" +
                "###rr" + "\n" +
                "#####"
        );
    }

    @Test
    public void Pauser_prevents_others_passing_on_slope_down_mid()
    {
        assertWorldEvolvesLike(
            "r r  " + "\n" +
                "###\\ " + "\n" +
                "####*" + "\n" +
                ":*=u\\",

            " r   " + "\n" +
                "###r " + "\n" +
                "####u",

            "  r  " + "\n" +
                "###\\ " + "\n" +
                "####r",

            "     " + "\n" +
                "###r " + "\n" +
                "####r",

            "     " + "\n" +
                "###\\ " + "\n" +
                "####r"
        );
    }

    @Test
    public void Pauser_prevents_others_passing_on_slope_down_end()
    {
        assertWorldEvolvesLike(
            "  j j" + "\n" +
                "u/###" + "\n" +
                "#####",

            "   j " + "\n" +
                "uj###" + "\n" +
                "#####",

            "  j  " + "\n" +
                "j/###" + "\n" +
                "#####",

            "     " + "\n" +
                "jj###" + "\n" +
                "#####",

            "     " + "\n" +
                "j/###" + "\n" +
                "#####"
        );
    }
}
