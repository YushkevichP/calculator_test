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


    }

    private fun initButtons() {
        with(binding) {

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
                appendSymbol('х')
            }


            buttonDivide.setOnClickListener {
                appendSymbol('/')
            }

            buttonEqual.setOnClickListener {
                // TODO:  equals logic
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

    private fun appendNumber(symbol: String) {
        if (binding.inputPlace.text[0] == '0') {
            binding.inputPlace.text = ""
        }
        binding.inputPlace.append(symbol)
    }

    private fun appendSymbol(symbol: Char) {

        val firstChar = binding.inputPlace.text[0]
        val listSymbols = mutableListOf<Char>('+', '-', 'х', '/', '.')

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
