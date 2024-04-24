package pl.careaboutit.ceidgapp.ui.screens.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.api.model.Company

@Composable
fun CompanyItem(company: Company) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(text = stringResource(id = R.string.company), fontWeight = FontWeight.Bold)
            Text(text = company.nazwa)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.enterpreneur), fontWeight = FontWeight.Bold)
            Text(text = "${company.wlasciciel.imie} ${company.wlasciciel.nazwisko}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.nip), fontWeight = FontWeight.Bold)
            Text(text = company.wlasciciel.nip)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.regon), fontWeight = FontWeight.Bold)
            Text(text = company.wlasciciel.regon)
        }
    }
}