//package rabbitescape.engine.logic;
//
//import static rabbitescape.engine.util.WorldAssertions.*;
//
//import org.junit.Test;
//
//public class TestClimbing
//{
//    @Test
//    public void Climb_start_and_end_immediately()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            "rc ##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            " r>##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "  G##" + "\n" +
//            "#####",
//
//            "   L " + "\n" +
//            "  r##" + "\n" +
//            "#####",
//
//            "   r>" + "\n" +
//            "   ##" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_start_continue_end()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "rc ##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            " r>##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "  G##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   ##" + "\n" +
//            "  F##" + "\n" +
//            "  r##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "  F##" + "\n" +
//            "  r##" + "\n" +
//            "   ##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "  F##" + "\n" +
//            "  r##" + "\n" +
//            "   ##" + "\n" +
//            "#####",
//
//            "   L " + "\n" +
//            "  r##" + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "#####",
//
//            "   r>" + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_quickly_to_slope()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            "   / " + "\n" +
//            "rc ##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   / " + "\n" +
//            " r>##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   / " + "\n" +
//            "  G##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   L " + "\n" +
//            "  r##" + "\n" +
//            "#####",
//
//            "    '" + "\n" +
//            "   r " + "\n" +
//            "   ##" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_eventually_to_slope()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            "   / " + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "rc ##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   / " + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            " r>##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   / " + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "  G##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   / " + "\n" +
//            "   ##" + "\n" +
//            "  F##" + "\n" +
//            "  r##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   / " + "\n" +
//            "  F##" + "\n" +
//            "  r##" + "\n" +
//            "   ##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   / " + "\n" +
//            "  F##" + "\n" +
//            "  r##" + "\n" +
//            "   ##" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   L " + "\n" +
//            "  r##" + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "#####",
//
//            "    '" + "\n" +
//            "   r " + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "   ##" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_starts_from_slope()
//    {
//        assertWorldEvolvesLike(
//            "    #" + "\n" +
//            "    #" + "\n" +
//            "   / " + "\n" +
//            "rc/ #" + "\n" +
//            "#####",
//
//            "    #" + "\n" +
//            "    #" + "\n" +
//            "   / " + "\n" +
//            " r~ #" + "\n" +
//            "#####",
//
//            "    #" + "\n" +
//            "    #" + "\n" +
//            "   $ " + "\n" +
//            "  r #" + "\n" +
//            "#####",
//
//            "    #" + "\n" +
//            "    #" + "\n" +
//            "   G " + "\n" +
//            "  / #" + "\n" +
//            "#####",
//
//            "    #" + "\n" +
//            "   F#" + "\n" +
//            "   r " + "\n" +
//            "  / #" + "\n" +
//            "#####",
//
//            "   F#" + "\n" +
//            "   r#" + "\n" +
//            "   / " + "\n" +
//            "  / #" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_starts_from_bridge()
//    {
//        assertWorldEvolvesLike(
//            "    #" + "\n" +
//            "    #" + "\n" +
//            "   ( " + "\n" +
//            "rc( #" + "\n" +
//            "#####",
//
//            "    #" + "\n" +
//            "    #" + "\n" +
//            "   ( " + "\n" +
//            " r~ #" + "\n" +
//            "#####",
//
//            "    #" + "\n" +
//            "    #" + "\n" +
//            "   $ " + "\n" +
//            "  r #" + "\n" +
//            "#####",
//
//            "    #" + "\n" +
//            "    #" + "\n" +
//            "   G " + "\n" +
//            "  ( #" + "\n" +
//            "#####",
//
//            "    #" + "\n" +
//            "   F#" + "\n" +
//            "   r " + "\n" +
//            "  ( #" + "\n" +
//            "#####",
//
//            "   F#" + "\n" +
//            "   r#" + "\n" +
//            "   ( " + "\n" +
//            "  ( #" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_doesnt_start_when_roof()
//    {
//        assertWorldEvolvesLike(
//            "   # " + "\n" +
//            "rc  #" + "\n" +
//            "#####",
//
//            "   # " + "\n" +
//            " r> #" + "\n" +
//            "#####",
//
//            "   # " + "\n" +
//            "  r>#" + "\n" +
//            "#####",
//
//            "   # " + "\n" +
//            "   ?#" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_stops_when_roof()
//    {
//        assertWorldEvolvesLike(
//            " #  /  \\ " + "\n" +
//            "  #  #  #" + "\n" +
//            "  #  #  #" + "\n" +
//            "rc#rc#rc#" + "\n" +
//            "#########",
//
//            " #  /  \\ " + "\n" +
//            "  #  #  #" + "\n" +
//            "  #  #  #" + "\n" +
//            " G# G# G#" + "\n" +
//            "#########",
//
//            " #  /  \\ " + "\n" +
//            "  #  #  #" + "\n" +
//            " F# F# F#" + "\n" +
//            " r# r# r#" + "\n" +
//            "#########",
//
//            " #  /  \\ " + "\n" +
//            " F# F# F#" + "\n" +
//            " r# r# r#" + "\n" +
//            "  #  #  #" + "\n" +
//            "#########",
//
//            " #  /  \\ " + "\n" +
//            " F# F# F#" + "\n" +
//            " r# r# r#" + "\n" +
//            "  #  #  #" + "\n" +
//            "#########",
//
//            " #  /  \\ " + "\n" +
//            " F# F# F#" + "\n" +
//            "  #  #  #" + "\n" +
//            "  #  #  #" + "\n" +
//            "#########",
//
//            " #  /  \\ " + "\n" +
//            " j# j# j#" + "\n" +
//            " f# f# f#" + "\n" +
//            " f# f# f#" + "\n" +
//            "#########"
//        );
//    }
//
//    @Test
//    public void Climb_doesnt_start_when_slope_roof()
//    {
//        assertWorldEvolvesLike(
//            "   / " + "\n" +
//            "rc  #" + "\n" +
//            "#####",
//
//            "   / " + "\n" +
//            " r> #" + "\n" +
//            "#####",
//
//            "   / " + "\n" +
//            "  r>#" + "\n" +
//            "#####",
//
//            "   / " + "\n" +
//            "   ?#" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_does_start_when_bridge_roof()
//    {
//        assertWorldEvolvesLike(
//            "   ( " + "\n" +
//            "rc  #" + "\n" +
//            "#####",
//
//            "   ( " + "\n" +
//            " r> #" + "\n" +
//            "#####",
//
//            "   ( " + "\n" +
//            "  r>#" + "\n" +
//            "#####",
//
//            "   ( " + "\n" +
//            "   G#" + "\n" +
//            "#####",
//
//            "   (L" + "\n" +
//            "   r#" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_starts_straight_after_token()
//    {
//        assertWorldEvolvesLike(
//            "    " + "\n" +
//            "rc##" + "\n" +
//            "####",
//
//            "    " + "\n" +
//            " G##" + "\n" +
//            "####",
//
//            "  L " + "\n" +
//            " r##" + "\n" +
//            "####",
//
//            "  r>" + "\n" +
//            "  ##" + "\n" +
//            "####"
//        );
//    }
//
//    @Test
//    public void Dont_climb_over_bridge()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            " (cj " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            " <j  " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "<j   " + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_over_slope()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            " /cj " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            " /T  " + "\n" +
//            "#####",
//
//            " U   " + "\n" +  // Climb over top
//            " /j  " + "\n" +
//            "#####",
//
//            " j   " + "\n" +  // Fall onto slope
//            " s   " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "+j   " + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_over_slope_at_top()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            " /   " + "\n" +
//            " #cj " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            " /   " + "\n" +
//            " #T  " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            " /Y  " + "\n" +
//            " #j  " + "\n" +
//            "#####",
//
//            " U   " + "\n" +
//            " /j  " + "\n" +
//            " #   " + "\n" +
//            "#####",
//
//            " j   " + "\n" +
//            " s   " + "\n" +
//            " #   " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "+j   " + "\n" +
//            " #   " + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Climb_then_climb_again()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            "     " + "\n" +
//            "  # #" + "\n" +
//            "rc# #" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "     " + "\n" +
//            "  # #" + "\n" +
//            " G# #" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "     " + "\n" +
//            " F# #" + "\n" +
//            " r# #" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "  L  " + "\n" +
//            " r# #" + "\n" +
//            "  # #" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "  r> " + "\n" +
//            "  # #" + "\n" +
//            "  # #" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   r " + "\n" +
//            "  #f#" + "\n" +
//            "  #f#" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "     " + "\n" +
//            "  # #" + "\n" +
//            "  #G#" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "     " + "\n" +
//            "  #F#" + "\n" +
//            "  #r#" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "    L" + "\n" +
//            "  #r#" + "\n" +
//            "  # #" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Small_climb_then_climb_again()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            "     " + "\n" +
//            "rc# #" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "     " + "\n" +
//            " G# #" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "  L  " + "\n" +
//            " r# #" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "  r> " + "\n" +
//            "  # #" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "   r " + "\n" +
//            "  #f#" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "     " + "\n" +
//            "  #G#" + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "    L" + "\n" +
//            "  #r#" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Cant_pick_up_tokens_when_climbing()
//    {
//        assertWorldEvolvesLike(
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            " b# d# i# k#" + "\n" +
//            " )# )# )# )#" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "rc#rc#rc#rc#" + "\n" +
//            "############",
//
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            " b# d# i# k#" + "\n" +
//            "  #  #  #  #" + "\n" +
//            " G# G# G# G#" + "\n" +
//            "############",
//
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            " b# d# i# k#" + "\n" +
//            " F# F# F# F#" + "\n" +
//            " r# r# r# r#" + "\n" +
//            "############",
//
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            " F# F# F# F#" + "\n" +
//            " r# r# r# r#" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "############",
//
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            " F# F# F# F#" + "\n" +
//            " r# r# r# r#" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "############",
//
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            " F# F# F# F#" + "\n" +
//            " b# d# i# k#" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "############",
//
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            " F# F# F# F#" + "\n" +
//            " b# d# i# k#" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "############",
//
//            "  #  #  #  #" + "\n" +
//            " F# F# F# F#" + "\n" +
//            " r# r# r# r#" + "\n" +
//            " b# d# i# k#" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "  #  #  #  #" + "\n" +
//            "############"
//        );
//    }
//
//    @Test
//    public void Dont_pick_up_climb_token_if_already_a_climber()
//    {
//        assertWorldEvolvesLike(
//            "     " + "\n" +
//            "rcc  " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            " r>  " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "  c> " + "\n" +
//            "#####",
//
//            "     " + "\n" +
//            "  cr>" + "\n" +
//            "#####"
//        );
//    }
//
//    @Test
//    public void Dont_revert_to_climber_after_digging_and_having_been_on_a_slope()
//    {
//        assertWorldEvolvesLike(
//            " cd " + "\n" +
//            "#j##" + "\n" +
//            "#(##" + "\n" +
//            "####" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "  d " + "\n" +
//            "#c##" + "\n" +
//            "#f##" + "\n" +
//            "####" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "  d " + "\n" +
//            "# ##" + "\n" +
//            "#G##" + "\n" +
//            "####" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "  d " + "\n" +
//            "#F##" + "\n" +
//            "#r##" + "\n" +
//            "####" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "  L " + "\n" +
//            "#r##" + "\n" +
//            "#(##" + "\n" +
//            "####" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "  r " + "\n" +
//            "# D#" + "\n" +
//            "#(##" + "\n" +
//            "####" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "    " + "\n" +
//            "# D#" + "\n" +
//            "#(##" + "\n" +
//            "####" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "    " + "\n" +
//            "# r#" + "\n" +
//            "#(D#" + "\n" +
//            "####" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "    " + "\n" +
//            "#  #" + "\n" +
//            "#(D#" + "\n" +
//            "####" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "    " + "\n" +
//            "#  #" + "\n" +
//            "#(r#" + "\n" +
//            "##D#" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "    " + "\n" +
//            "#  #" + "\n" +
//            "#( #" + "\n" +
//            "##D#" + "\n" +
//            "#) (" + "\n" +
//            "#) (",
//
//            "    " + "\n" +
//            "#  #" + "\n" +
//            "#( #" + "\n" +
//            "##r#" + "\n" +
//            "#)f(" + "\n" +
//            "#)f(",
//
//            "    " + "\n" +
//            "#  #" + "\n" +
//            "#( #" + "\n" +
//            "## #" + "\n" +
//            "#) (" + "\n" +
//            "#)r("
//        );
//
//
//    }
//}
