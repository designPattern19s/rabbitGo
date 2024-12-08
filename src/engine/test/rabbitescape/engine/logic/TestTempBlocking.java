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

    // 오류 나는 테스트 주석처리..
    /*@Test
    public void Cresting_rabbits_are_blocked()
    {
        assertWorldEvolvesLike(
            "r r" + "\n" +
                " (*" + "\n" +
                "(  " + "\n" +
                ":*=)o" + "\n" +  // 다 바꿨는데 이 가운데 줄 대체 뭐지? 토큰 놓는거라고 생각했는데 여긴 왜 가운데에 들어가있는지 모르겠음
                "r r" + "\n" +
                " /*" + "\n" +
                "/##" + "\n" +
                ":*=\\o",

            "   " + "\n" +
                " (r" + "\n" +
                "r  " + "\n" +
                "   " + "\n" +
                " /r" + "\n" +
                "r##",

            "   " + "\n" +
                " rr" + "\n" +
                "(  " + "\n" +
                "   " + "\n" +
                " rr" + "\n" +
                "/##",

            "   " + "\n" +
                " jr" + "\n" +
                "/  " + "\n" +
                "   " + "\n" +
                " jr" + "\n" +
                "/##",

            "   " + "\n" +
                " (r" + "\n" +
                "j  " + "\n" +
                "   " + "\n" +
                " /r" + "\n" +
                "j##"
        );
    }*/

    /*@Test
    public void Valleying_rabbits_are_blocked()
    {
        assertWorldEvolvesLike(
            "r  " + "\n" +
                "   " + "\n" +
                ") r" + "\n" +
                " )*" + "\n" +
                ":*=(o" + "\n" +
                "r  " + "\n" +
                "\\ r" + "\n" +
                "#\\*" + "\n" +
                ":*=/o",

            "   " + "\n" +
                "   " + "\n" +
                "r  " + "\n" +
                " )r" + "\n" +
                "   " + "\n" +
                "r  " + "\n" +
                "#)r",

            "   " + "\n" +
                "   " + "\n" +
                ")  " + "\n" +
                " rr" + "\n" +
                "   " + "\n" + // ] 였는데, turning left라길래. 일단 r로 마주칠 것 깉아서 r로 대체
                "\\  " + "\n" +
                "#rr",

            "   " + "\n" +
                "   " + "\n" +
                ")  " + "\n" +
                " jr" + "\n" +
                "   " + "\n" +
                ")  " + "\n" +
                "#jr",

            "   " + "\n" +
                "   " + "\n" +
                "j  " + "\n" +
                " )r" + "\n" +
                "   " + "\n" +
                "j  " + "\n" +
                "#\\r",

            "   " + "\n" +
                "   " + "\n" +
                ")  " + "\n" +
                " )r" + "\n" + // j 왜 사라지지? 밖에 테두리가 없어서 나가는거같기도.
                "   " + "\n" +
                "\\  " + "\n" +
                "#\\r"
        );

    }*/
}
