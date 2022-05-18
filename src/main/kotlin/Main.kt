import kotlin.math.ceil

internal class Main {
}

fun main() {
    val times = movie(500, 15, 0.9)
}

fun movie(card: Int, ticket: Int, perc: Double): Int {
    val cards = cardPrices(card, ticket.toDouble(), perc)
    val tickets = normalPrices(ticket)

    return cards
        .zip(tickets)
        .indexOfFirst { val (cardPrice, normalPrice) = it
            cardPrice < normalPrice
        }.plus(1)
}

fun normalPrices(ticketPrice: Int) = sequence {
    var nTicketsPrice = 0
    while (true) {
        nTicketsPrice += ticketPrice
        yield(nTicketsPrice)
    }
}

fun cardPrices(baseCardPrice: Int, basePrice: Double, prec: Double) = sequence {
    var totalPrice = baseCardPrice.toDouble()
    var previousTicketPrice = basePrice
    while (true) {
        val actualPrice = previousTicketPrice * prec
        totalPrice += actualPrice
        yield(ceil(totalPrice))
        previousTicketPrice = actualPrice
    }
}
