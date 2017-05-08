package nl.nuggit.kalah;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/")
public class Main extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");
        if (game == null) {
            String playerA = request.getParameter("playerA");
            String playerB = request.getParameter("playerB");
            if (playerA == null || playerB == null) {
                request.getRequestDispatcher("/WEB-INF/start.jsp").forward(request, response);
                return;
            }
            game = new Game(playerA, playerB);
            session.setAttribute("game", game);
        }
        request.setAttribute("game", game);
        if (game.isGameEnded()) {
            request.getRequestDispatcher("/WEB-INF/end.jsp").forward(request, response);
            return;
        }
        String moveParameter = request.getParameter("move");
        if (moveParameter == null) {
            request.getRequestDispatcher("/WEB-INF/move.jsp").forward(request, response);
            return;
        }
        int pit = Integer.parseInt(moveParameter);
        Move move = new Move(game);
        move.from(pit);
        request.getRequestDispatcher("/WEB-INF/move.jsp").forward(request, response);
    }

}