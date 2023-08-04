package com.example.animateio.data

import androidx.annotation.DrawableRes
import com.example.animateio.R

data class Contact(
    val name: String,
    val email: String,
    val phone: String,
    val description: String,
    @DrawableRes val icon: Int
)

val contactList: List<Contact> = listOf(
    Contact(
        name = "Bill Gates",
        email = "bill@gates.com",
        phone = "+010129010",
        description = "William Henry Gates III is an American business magnate, investor, and philanthropist. He is best known for co-founding software giant Microsoft, along with his childhood friend Paul Allen.",
        icon = R.drawable.bill_gates
    ),
    Contact(
        name = "Oprah Winfrey",
        email = "oprah@winfrey.com",
        phone = "+0101296272",
        description = "Oprah Gail Winfrey is an American talk show host, television producer, actress, author, and media proprietor. She is best known for her talk show, The Oprah Winfrey Show, broadcast from Chicago, which ran in national syndication for 25 years, from 1986 to 2011.",
        icon = R.drawable.oprah_winfrey
    ),
    Contact(
        name = "Linus Torvalds",
        email = "linus@linux.com",
        phone = "+9010129010",
        description = "Linus Benedict Torvalds is a Finnish software engineer who is the creator and, historically, the lead developer of the Linux kernel, used by Linux distributions and other operating systems such as Android. He also created the distributed version control system Git.",
        icon = R.drawable.linus_torvalds
    ),
    Contact(
        name = "Madam Curie",
        email = "curie@radium.com",
        phone = "+9710129010",
        description = "Marie Salomea Skłodowska-Curie was a Polish and naturalized-French physicist and chemist who conducted pioneering research on radioactivity. She was the first woman to win a Nobel Prize, the first person to win a Nobel Prize twice, and the only person to win a Nobel Prize in two scientific fields.",
        icon = R.drawable.marie_curie
    ),
    Contact(
        name = "Terrence Tao",
        email = "tao@gterrence.com",
        phone = "+010129010",
        description = "Terence Chi-Shen Tao FAA FRS is an Australian mathematician. He is a professor of mathematics at the University of California, Los Angeles, where he holds the James and Carol Collins chair",
        icon = R.drawable.terry_tao
    ),
)