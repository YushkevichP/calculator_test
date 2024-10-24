package com.example.calculator2024

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.calculator2024.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "Binding problem"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCalculatorBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtons()

        with(binding) {
            resultPlace.text = "Тут будет результат"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initButtons() {
        with(binding) {

//           val listOfPair =  listOf(
//                button0 to "0",
//                button2 to "1",
//                button3 to "1",
//                button4 to "1",
//                button5 to "1",
//                button6 to "1",
//                button7 to "1",
//                button8 to "1",
//                button9 to "1",
//                buttonAC to "1",
//                buttonBackspace to "1",
//                buttonBraces to "1",
//                button to "1",
//                button to "1",
//                button to "1",
//                button to "1",
//                button to "1",
//                button to "1",
//            )
//                .forEach { pair ->
//                    pair.first.setOnClickListener {
//                        appendNumber(pair.second)
//                    }
//                }

            button0.setOnClickListener {
                appendNumber("0")
            }

            button1.setOnClickListener {
                appendNumber("1")
            }

            button2.setOnClickListener {
                appendNumber("2")
            }

            button3.setOnClickListener {
                appendNumber("3")
            }

            button4.setOnClickListener {
                appendNumber("4")
            }
            button5.setOnClickListener {
                appendNumber("5")
            }

            button6.setOnClickListener {
                appendNumber("6")
            }

            button7.setOnClickListener {
                appendNumber("7")
            }

            button8.setOnClickListener {
                appendNumber("8")
            }

            button9.setOnClickListener {
                appendNumber("9")
            }

            buttonAC.setOnClickListener {
                inputPlace.text = "0"
            }

            buttonBraces.setOnClickListener {
                appendNumber("(")
            }

            buttonBackspace.setOnClickListener {
                deleteSymbol()
            }

            buttonPlus.setOnClickListener {
                appendSymbol('+')
            }

            buttonMinus.setOnClickListener {
                appendSymbol('-')
            }

            buttonX.setOnClickListener {
                appendSymbol('*')
            }

            buttonDivide.setOnClickListener {
                appendSymbol('/')
            }

            buttonEqual.setOnClickListener {
                toEqual()
            }

            buttonDot.setOnClickListener {
                appendSymbol('.')
            }
        }
    }

    private fun deleteSymbol() {
        val inputText = binding.inputPlace.text
        when {
            inputText.length > 1 -> {
                binding.inputPlace.text = inputText.dropLast(1)
                println("OK OK OK OK ")
                println(binding.inputPlace.text)
            }

            inputText.length == 1 -> {
                binding.inputPlace.text = "0"
            }
        }
    }

    private fun appendNumber(number: String) {
        if (binding.inputPlace.text[0] == '0') {
            binding.inputPlace.text = ""
        }
        binding.inputPlace.append(number)
    }

    private fun appendSymbol(symbol: Char) {

        val firstChar = binding.inputPlace.text[0]
        val lastChar = binding.inputPlace.text.last()
        val listSymbols = mutableListOf('+', '-', '*', '/', '.')
        val inputText = binding.inputPlace.text

        //проверка, чтоб не могли поставиьт несколько знаков подряд
        if (lastChar in listSymbols) {
            binding.inputPlace.text = inputText.dropLast(1)
        }

        if (symbol == '.') {
            val lastNumber = inputText.split(Regex("[+\\-*/]")).lastOrNull()

            if (lastNumber != null && lastNumber.contains('.')) {
                // Если в последнем числе уже есть точка, не добавлять её
                Toast.makeText(
                    requireContext(),
                    "This number already has a dot",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
        }

        if (firstChar == '0') {
            //добавлена проверка на первый символ в строке (если это математический, то сообщение)
            if (symbol in listSymbols) {
                Toast.makeText(requireContext(), "Please input a number", Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.inputPlace.text = ""
                binding.inputPlace.append(symbol.toString())
            }
        } else binding.inputPlace.append(symbol.toString())
    }

    private fun toEqual() {
        val workString = binding.inputPlace.text.toString()

        try {
            val finalResult = Calculator().calculateNoBraces(workString)
            binding.resultPlace.text = finalResult.toString()
            binding.inputPlace.text = finalResult.toString()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "На ноль нельзя делить!!!!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}




