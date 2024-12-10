package rabbitescape.engine.logic;

import static rabbitescape.engine.util.WorldAssertions.assertWorldEvolvesLike;

import org.junit.Test;

public class TestTempBlocking
{
    @Test
    public void Blocker_stands_still()
    {
        assertWorldEvolvesLike(
            " ro " + "\n" +
                "####",

            "  r " + "\n" +
                "####",

            "  r " + "\n" +
                "####",

            "  r " + "\n" +
                "####"
        );
    }

    @Test
    public void Blocker_prevents_others_passing()
    {
        assertWorldEvolvesLike(
            "r ro " + "\n" +
                "#####",

            " r r " + "\n" +
                "#####",

            "  rr " + "\n" +
                "#####",

            "  jr " + "\n" + // 방향전환하면 이렇게 바뀌더라
                "#####"
        );
    }

    @Test
    public void Blocker_no_prevents_others_passing() // 내가 만든거
    {
        assertWorldEvolvesLike(
            "r      ro " + "\n" +
                "##########",

            " r      r " + "\n" +
                "##########",

            "  r     r " + "\n" +
                "##########",

            "   r    r " + "\n" +
                "##########",

            "    r   r " + "\n" +
                "##########",

            "     r  r " + "\n" +
                "##########",

            "      r r " + "\n" + // 멈췄다가 가도록?
                "##########",

            "       r r" + "\n" +
                "##########"

        );
    }

    @Test
    public void Blocker_prevents_others_passing_on_slope_up_start()
    {
        assertWorldEvolvesLike(
            "r r* " + "\n" +
                "#####" + "\n" +
                ":*=o/",

            " r r " + "\n" +
                "#####",

            "  rr " + "\n" +
                "#####",

            "  jr " + "\n" +
                "#####"
        );
    }

    @Test
    public void Blocker_prevents_others_passing_on_slope_up_mid()
    {
        assertWorldEvolvesLike(
            "     /" + "\n" +
                "    *#" + "\n" +
                "r r/##" + "\n" +
                "######" + "\n" +
                ":*=o/",

            "     /" + "\n" +
                "    o#" + "\n" + // 이거 토큰 놔져야해서 o로 했는데 아닐 가능성도 있음
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
                "   j##" + "\n" +
                "######",

            "     /" + "\n" +
                "    r#" + "\n" +
                "  j/##" + "\n" +
                "######"
            );
    }

    @Test
    public void Blocker_prevents_others_passing_on_slope_up_end()
    {
        assertWorldEvolvesLike(
            "     o" + "\n" +
                "    /#" + "\n" +
                "r r/##" + "\n" +
                "######",

            "     o" + "\n" +
                "    /#" + "\n" +
                " r r##" + "\n" +
                "######",

            "     o" + "\n" +
                "    r#" + "\n" +
                "  r/##" + "\n" +
                "######",

            "     r" + "\n" +
                "    /#" + "\n" +
                "   r##" + "\n" +
                "######",

            "     r" + "\n" +
                "    r#" + "\n" +
                "   /##" + "\n" +
                "######",

            "     r" + "\n" +
                "    j#" + "\n" +
                "   /##" + "\n" +
                "######"
        );
    }

    @Test
    public void Blocker_prevents_others_passing_on_slope_down_start()
    {
        assertWorldEvolvesLike(
            "r r  " + "\n" +
                "###* " + "\n" +
                "#####" + "\n" +
                ":*=o\\",

            " r   " + "\n" +
                "###r " + "\n" +
                "#####",

            "  r  " + "\n" +
                "###r " + "\n" +
                "#####",

            "  j  " + "\n" +
                "###r " + "\n" + // 바
                "#####"
        );
    }

    @Test
    public void Blocker_prevents_others_passing_on_slope_down_mid()
    {
        assertWorldEvolvesLike(
            "r r  " + "\n" +
                "###\\ " + "\n" +
                "####*" + "\n" +
                ":*=o\\",

            " r   " + "\n" +
                "###r " + "\n" + // 이거도 o 아닐수있음
                "####o",

            "  r  " + "\n" +
                "###\\ " + "\n" +
                "####r",

            "     " + "\n" +
                "###r " + "\n" +
                "####r",

            "     " + "\n" +
                "###j " + "\n" +
                "####r"
        );
    }

    @Test
    public void Blocker_prevents_others_passing_on_slope_down_end()
    {
        assertWorldEvolvesLike(
            "  j j" + "\n" +
                "o/###" + "\n" +
                "#####",

            "   j " + "\n" +
                "oj###" + "\n" +
                "#####",

            "  j  " + "\n" +
                "j/###" + "\n" + // 왼쪽으로 가다가 먹으면 j상태로 멈춤
                "#####",

            "     " + "\n" +
                "jj###" + "\n" +
                "#####",

            "     " + "\n" +
                "jr###" + "\n" +
                "#####"
        );
    }

    @Test
    public void Blocker_no_prevents_others_passing_on_slope_end()
    {
        assertWorldEvolvesLike(

            "          o" + "\n" +
                "         /#" + "\n" +
                "r      r/##" + "\n" +
                "###########",

            "          o" + "\n" +
                "         /#" + "\n" +
                " r      r##" + "\n" +
                "###########",

            "          o" + "\n" +
                "         r#" + "\n" +
                "  r     /##" + "\n" +
                "###########",

            "          r" + "\n" +
                "         /#" + "\n" +
                "   r    /##" + "\n" +
                "###########",

            "          r" + "\n" +
                "         /#" + "\n" +
                "    r   /##" + "\n" +
                "###########",

            "          r" + "\n" +
                "         /#" + "\n" +
                "     r  /##" + "\n" +
                "###########",

            "          r" + "\n" +
                "         /#" + "\n" +
                "      r /##" + "\n" +
                "###########",

            "          r" + "\n" +
                "         /#" + "\n" +
                "       r/##" + "\n" +
                "###########",

            "          r" + "\n" +
                "         /#" + "\n" +
                "        r##" + "\n" +
                "###########",

            "           " + "\n" +
                "         r#" + "\n" +
                "        /##" + "\n" +
                "###########",

            "          r" + "\n" +
                "         /#" + "\n" +
                "        /##" + "\n" +
                "###########"

            );
    }

    @Test
    public void Blocker_no_prevents_others_passing_on_slope_down_end()
    {
        assertWorldEvolvesLike(

            "  j     j" + "\n" +
                "o/#######" + "\n" +
                "#########",

            "       j " + "\n" +
                "oj#######" + "\n" +
                "#########",

            "      j  " + "\n" +
                "j/#######" + "\n" + // 왼쪽으로 가다가 먹면 j상태로 멈춤
                "#########",

            "     j   " + "\n" +
                "j/#######" + "\n" +
                "#########",

            "    j    " + "\n" +
                "j/#######" + "\n" +
                "#########",

            "   j     " + "\n" +
                "j/#######" + "\n" +
                "#########",

            "  j      " + "\n" +
                "j/#######" + "\n" +
                "#########",

            "         " + "\n" +
                "jj#######" + "\n" +
                "#########",

            "         " + "\n" +
                "j/#######" + "\n" +
                "#########"
        );
    }
}
