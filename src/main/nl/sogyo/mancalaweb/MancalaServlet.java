package nl.sogyo.mancalaweb;

import nl.sogyo.mancala.backend.*;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MancalaServlet
 */
// @WebServlet("/MancalaServlet")
public class MancalaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MancalaServlet() {
		super();
	}

	private void choosePage(HttpServletRequest request, HttpServletResponse response, Mancala man)
			throws ServletException, IOException {
		String winnerString = "";
		Winner winner = man.getWinner();
		if (winner == Winner.PLAYER_ONE) {
			winnerString = "Player one has won!";
		} else if (winner == Winner.PLAYER_TWO) {
			winnerString = "Player two has won!";
		} else if (winner == Winner.DRAW) {
			winnerString = "It was a draw!";
		} else {
			winnerString = "An error occurred, please contact the makers of this page.";
		}
		MancalaStateBean msb = new MancalaStateBean(man.getStoneAmounts(), man.getCurrentTurn() + 1, winnerString);
		request.setAttribute("currentState", msb);
		if (man.hasWinner()) {
			RequestDispatcher rd = request.getRequestDispatcher("showWinner.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("showCurrentState.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("MancalaSessionBean") == null) {
			session.setAttribute("MancalaSessionBean", new MancalaSessionBean(new Mancala()));
		}
		Mancala man = ((MancalaSessionBean) session.getAttribute("MancalaSessionBean")).getMancala();
		choosePage(request, response, man);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MancalaSessionBean msb = ((MancalaSessionBean) session.getAttribute("MancalaSessionBean"));
		if (msb == null) {
			doGet(request, response);
		} else {
			Mancala man = msb.getMancala();
			BufferedReader read = request.getReader();
			try {
				String line = read.readLine();
				String[] split = line.split("=");
				if (split[0].equals("newGame")) {
					man = new Mancala();
					session.setAttribute("MancalaSessionBean", new MancalaSessionBean(man));
				} else if (split[0].equals("doMove")) {
					Integer location = Integer.parseInt(split[1]);
					man.doMove(location - 7 * man.getCurrentTurn() + 1);
				} else {
					throw new IllegalArgumentException("Unknown POST option");
				}
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			// session.setAttribute("MancalaSessionBean", man);
			choosePage(request, response, man);
		}
	}

}
