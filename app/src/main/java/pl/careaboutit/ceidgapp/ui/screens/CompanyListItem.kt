package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.data.model.CompanyElementDisplayable

@Composable
fun CompanyListItem(
    company: CompanyElementDisplayable,
    clicked: (String) -> Unit
) {
    OutlinedCard(
        modifier = Modifier.clickable {
            clicked(company.id)
        },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    text = company.nazwa,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }

        HorizontalDivider(color = Color.Black)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(4f),
                        text = stringResource(id = R.string.enterpreneur),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        modifier = Modifier.weight(6f),
                        text = "${company.wlasciciel.imie} ${company.wlasciciel.nazwisko}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(4f),
                        text = stringResource(id = R.string.nip),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        modifier = Modifier.weight(6f),
                        text = company.wlasciciel.nip ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(4f),
                        text = stringResource(id = R.string.regon),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        modifier = Modifier.weight(6f),
                        text = company.wlasciciel.regon ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(4f),
                        text = stringResource(id = R.string.city),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        modifier = Modifier.weight(6f),
                        text = company.miasto,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

}