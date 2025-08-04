package de.seb.discord.bot;

import java.util.List;
import java.util.Random;

public class CringeMessages {

    public static final List<String> DEAL_INTROS = List.of(
            "Ayo besties 😩✋ stay hydrated Challenge 2025 💧🫧 – diese Drinks sind lowkey Main Character vibes rn 🔥📈",
            "ngl 💀 diese Preise sind giving Free Refill Energy 🥤🪩 – grab now, cry never 🙏✨",
            "no thoughts, just sip 🧃😶‍🌫️ – diese Deals sind literally built different 🚀🤑",
            "bruh 💀💦 hydrate or diedrate – und zwar mit diesen giga slay Angeboten 🔥💧",
            "ok aber… diese Getränke-Preise? literally a steal 🔫💸 – FBI open up 🚪💥",
            "sip happens 🍹🙃 – aber bei dem Preis ist’s basically Self Care 🛀💅",
            "Hydration Level: Giga Chad 💪💧 – sponsored by mein Wallet crying rn 😭🤑",
            "Y’all better sip before they dip 🥤🏃💨 – hotter als dein Situationship rn ❤️‍🔥💔",
            "Bro, diese Drinks sind bussin fr fr 😤🍹 – zero cap, only drip 💧💯",
            "Drink up Buttercup 🌼🫧 – diese Deals hitten härter als Montagmorgen Espresso ☕💥",
            "Stay sippin’ King 👑💦 – diese Preise sind pure Serotonin Boost 🌈✨",
            "Bet, diese Deals sind giving 2-for-1-core vibes 🍾💨 – no one’s Wallet is safe 🤑🔥",
            "Literally einfach Liquid Riz 🥤💘 – pull up & flex Hydration Level 100 🚀💧",
            "Straight up Dopamin im Becher 🧋⚡ – warum trinkt das nicht jeder rn??",
            "Cheers Babes 🥂🫶 – diese Angebote sind basically Public Service Announcement 📢💦",
            "Hydration Drip Check 💧👟 – diese Drinks sind mehr Style als dein OOTD 📸✨",
            "Lowkey der Glow-Up für deine Wasserbilanz 🌊💅 – 100% Slay Approved ✅",
            "Thirst Trap but make it affordable 🥵🥤 – smoother als Oat Milk Latte Vibes 🥛✨",
            "Hydration Arc unlocked 🗝️💧 – let’s speedrun diese Angebote fr fr ⚡",
            "Pls, diese Preise sind so soft 🌸🧃 – du robbst den Laden basically rn 💸💀",
            "Digga 😎💧 diese Preise sind nicht nur Baba – die sind gottlos schere ✂️🥤",
            "Goofy cheap vibes rn 🤡💸 – mies Aura im Becher 💫🥤",
            "Brudi 😩💦 diese Deals haben mehr Riz als dein Crush auf Insta 💘📱",
            "Side-Eye 😏👀 – diese Preise sind so wild, sogar meine Oma würde flexen 🪩🥤",
            "Mieser Aura-Boost 💫🥤 – schon beim Anblick wird dein Durst AFK gehen 🏃💨",
            "Ohaaa 🥶 diese Angebote sind cringe günstig – und das mein ich positiv 😏✨",
            "Baba Hydration 🔥💧 – gönn dir, bevor’s sus wird 🚩",
            "Mies wild 🤯💦 – fast schon zu gut um kein Scam zu sein 💸👀",
            "Bro 😭💧 das ist literally die Endgegner-Version von Durst löschen 🛡️🥤",
            "Schere ✂️💦 – cut dir direkt einen von diesen Baba-Deals rein",
            "Aura-Level: Giga Chad 💪💧 – sponsored by dein Durst 3000 🚀",
            "Riz & Sip 💘🥤 – diese Drinks kriegen jedes Herz zum Schmelzen 🫠",
            "Side-Eye + Hydration = OP-Kombo 💧😏 – gönn, bevor’s alle wissen 📢",
            "Cringe günstig 😅💦 – und trotzdem mies lecker 🥤💖",
            "Goofy Angebot 🤡📉 – mein Geldbeutel macht grad Happy Dance 💃💸",
            "Schere im Kopf, aber die Angebote im Herzen 🥹💧",
            "Aura-Kick 💫🥤 – schon ein Schluck und du bist Main Character",
            "Flex-Level 9000 🥤💪 – stay hydrated und bleib Baba 🫶",
            "Wildeste Deals seit TikTok erfunden wurde 📱🔥",
            "Goofy günstig, mies lecker, Baba Durstlöscher 🥤💧"
    );

    public static final List<String> NO_DEAL_INTROS = List.of(
            "Sorry Brudi 😔🥤 heute keine Spezi-Vibes… musst wohl Leitungswasser flexen 💧💀",
            "Big oof 💀🥶 – heute leider 0,0% Angebote… nur pure FOMO energy 🚫🍹",
            "Ngl 😩💦 – keine Deals heute, stay strong King 👑💧",
            "Bruh 😭🫠 – Angebot leer wie mein Akku um 3 Uhr nachts 📉🔋",
            "Heute? Nur Enttäuschungcore vibes 💔🥤 – sip happens I guess 😭💧",
            "Ey Diggi 😩🚫 – kein Drip im Angebot, nur stilles Wasser und stille Tränen 💦😔",
            "Oha 😱🥤 – heute echt nix am Start… hydrate dich einfach mit Hoffnung 💧✨",
            "Bratan 😭💀 – heute keine Getränke-Deals, aber dafür gute Laune gratis (hoffentlich) 🌈🥤",
            "F in the chat 💀📉 – Angebote sind heute komplett AFK gegangen 🚪🏃💨",
            "Brooo 😩🥤 – heute nur Imaginary Drinks im Angebot… stay hydrated in your dreams 💧😴",
            "Sorry Brudi 😔🥤 – heute keine Baba-Deals, nur Goofy Leitungswasser 🤡💧",
            "Schere ✂️💦 – das Angebot ist heute einfach abgeschnitten",
            "Aura ist heute low 😔💧 – kein Baba-Deal in Sicht",
            "Side-Eye 😏 – das hier ist mies leer, sogar mein Kühlschrank hat mehr zu bieten 🥶",
            "Goofy Moment 🤡📉 – heute keine Drinks, nur Durst",
            "Baba? Eher Blamage 😭💦 – nix da zum flexen",
            "Wild wie leer es hier ist 😩🥤 – Durst bleibt heute Endgegner",
            "Digga 🥶 – heute nur H2O Edition 💧",
            "Cringe, aber true 😔 – keine Angebote gefunden",
            "Aura ist weg 😭💦 – bleibt nur trauriges Leitungswasser"
    );

    public static String getRandom(List<String> list) {
        int randomIndex = new Random().nextInt(list.size());
        return list.get(randomIndex);
    }

}
