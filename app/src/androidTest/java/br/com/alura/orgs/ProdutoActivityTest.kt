package br.com.alura.orgs

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.ui.activity.FormularioProdutoActivity
import br.com.alura.orgs.ui.activity.ListaProdutosActivity
import org.junit.Before
import org.junit.Test

class ProdutoActivityTest {

    @Before
    fun preparaAmbiente() {
        AppDatabase.instancia(
            InstrumentationRegistry.getInstrumentation().targetContext
        ).clearAllTables()
    }

    @Test
    fun deveMostrarONomeDoAplicativo() {
        launch(ListaProdutosActivity::class.java)
        onView(withText("Orgs")).check(matches(isDisplayed()))
    }

    @Test
    fun deveMostrarCamposNecessarioParaCriarUmProduto() {
        launch(FormularioProdutoActivity::class.java)

        onView(withId(R.id.activity_formulario_produto_nome)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_formulario_produto_descricao)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_formulario_produto_valor)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_formulario_produto_botao_salvar)).check(matches(isDisplayed()))
    }

    @Test
    fun deveSerCapazDePreencherOsCamposESalvar() {
        launch(ListaProdutosActivity::class.java)

        onView(withId(R.id.activity_lista_produtos_fab))
            .perform(click())

        onView(withId(R.id.activity_formulario_produto_nome))
            .perform(
                typeText("Banana"),
                closeSoftKeyboard()
            )
        onView(withId(R.id.activity_formulario_produto_descricao))
            .perform(
                typeText("banana prata"),
                closeSoftKeyboard()
            )
        onView(withId(R.id.activity_formulario_produto_valor))
            .perform(
                typeText("6.99"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_botao_salvar))
            .perform(click())

        onView(withText("Banana")).check(matches(isDisplayed()))
    }

    @Test
    fun deveSerCapazDeEditarUmProduto() {
        launch(ListaProdutosActivity::class.java)

        onView(withId(R.id.activity_lista_produtos_fab))
            .perform(click())

        onView(withId(R.id.activity_formulario_produto_nome))
            .perform(
                typeText("Banana nanica"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_descricao))
            .perform(
                typeText("Da feira"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_valor))
            .perform(
                typeText("5.99"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_botao_salvar))
            .perform(click())


        onView(withText("Banana nanica"))
            .perform(click())

        onView(withId(R.id.menu_detalhes_produto_editar))
            .perform(click())

        onView(withId(R.id.activity_formulario_produto_nome))
            .perform(
                replaceText("Banana prata"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_descricao))
            .perform(
                replaceText("da vendinha"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_valor))
            .perform(
                replaceText("3.99"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_produto_botao_salvar))
            .perform(click())

        onView(withText("Banana prata"))
            .check(matches(isDisplayed()))
    }
}